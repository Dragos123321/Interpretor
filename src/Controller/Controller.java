package Controller;

import Model.Adt.IList;
import Model.Adt.JList;
import Model.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repo.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private final IRepo repo;
    private ExecutorService executor;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    private void oneStepForAllProgram(IList<PrgState> programs) throws InterruptedException {
        programs.getInner().forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException err) {
                err.printStackTrace();
            }
        });

        List<Callable<PrgState>> callList = programs.getInner().stream().filter(PrgState::isNotCompleted)
                .map((PrgState prg) -> (Callable<PrgState>) (() -> {
                    try {
                        return prg.oneStep();
                    } catch (Exception err) {
                        System.out.println(err.getMessage());
                        return null;
                    }
                })).collect(Collectors.toList());

        List<PrgState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException err) {
                        System.out.println("End of program");
                    }
                    return null;
                }).filter(Objects::nonNull).collect(Collectors.toList());

        programs.getInner().addAll(newProgramList);
        programs.getInner().forEach(prg -> {
            prg.getHeap().setContent(garbage_collector(get_used_addresses(prg.getSymTable().getContent().values(),
                    prg.getHeap().getContent().values()), prg.getHeap().getContent()));
        });

        programs.getInner().forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(programs);
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        IList<PrgState> programs = new JList<>();
        programs.setList(removeCompletedPrg(repo.getPrgList()));
        try {
            while (programs.getInner().size() > 0) {
                oneStepForAllProgram(programs);
                programs.setList(removeCompletedPrg(repo.getPrgList()));
            }
        } catch (InterruptedException err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        executor.shutdownNow();
        repo.setPrgList(programs);
    }

    private List<Integer> get_used_addresses(Collection<IValue> symTableValues, Collection<IValue> heapTableValues) {
        List<Integer> symTableAddresses = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getValue();
                })
                .collect(Collectors.toList());

        List<Integer> heapTableAddresses = heapTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getValue();
                })
                .collect(Collectors.toList());

        symTableAddresses.addAll(heapTableAddresses);
        return symTableAddresses;
    }

    private Map<Integer, IValue> garbage_collector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<PrgState> removeCompletedPrg(IList<PrgState> inPrgList) {
        return inPrgList.getInner().stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
