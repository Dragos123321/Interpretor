package Controller;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ControllerError;
import Model.PrgState;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repo.IRepo;

import java.io.IOException;
import java.util.*;
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

    private void oneStepForAllProgram(List<PrgState> programs) throws ControllerError {
        for (PrgState program : programs) {
            try {
                repo.logPrgStateExec(program);
            } catch (IOException error) {
                throw new ControllerError(error.getMessage());
            }
        }

        List<Callable<PrgState>> call_list = programs.stream()
                .map((PrgState program) -> (Callable<PrgState>) (program::oneStep))
                .collect(Collectors.toList());

        try {
            List<PrgState> new_programs_list = executor.invokeAll(call_list).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException | InterruptedException error) {
                            System.out.println(error.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            programs.addAll(new_programs_list);

            for (PrgState program : programs) {
                try {
                    repo.logPrgStateExec(program);
                } catch (IOException error) {
                    throw new ControllerError(error.getMessage());
                }
            }

            repo.setPrgList(programs);
        } catch (InterruptedException ignored) {
        }
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> programs = removeCompletedPrg(repo.getPrgList());
        try {
            while (programs.size() > 0) {
                IHeap<IValue> shared_heap = programs.get(0).getHeap();
                List<IDict<String, IValue>> all_sym_tables = programs.stream().
                        map(PrgState::getSymTable).collect(Collectors.toList());
                List<Integer> all_addresses_from_sym_tables = new ArrayList<>();
                all_sym_tables.stream()
                                .map(table -> get_used_addresses_from_sym_table(table.getContent().values()))
                                        .forEach(all_addresses_from_sym_tables::addAll);

                shared_heap.setContent(garbage_collector(all_addresses_from_sym_tables, shared_heap.getContent()));

                oneStepForAllProgram(programs);
                programs = removeCompletedPrg(repo.getPrgList());
            }
        } catch (ControllerError err) {
            System.out.println(err.getMessage());
            System.exit(1);
        }
        executor.shutdownNow();
        repo.setPrgList(programs);
    }

    private List<Integer> get_used_addresses_from_sym_table(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getValue();
                })
                .collect(Collectors.toList());
    }

    private List<Integer> get_used_addresses_from_heap(Collection<IValue> heapTableValues) {
        return heapTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {
                    RefValue value2 = (RefValue) value;
                    return value2.getValue();
                })
                .collect(Collectors.toList());
    }

    private Map<Integer, IValue> garbage_collector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        List<Integer> addresses_references_from_heap = get_used_addresses_from_heap(heap.values());
        return heap.entrySet().stream()
                .filter(elem -> (symTableAddr.contains(elem.getKey()) || addresses_references_from_heap.contains(elem.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
}
