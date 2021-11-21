package Controller;
import Model.Adt.IList;
import Model.Exceptions.ControllerError;
import Model.PrgState;
import Model.Adt.IStack;
import Model.Statements.IStmt;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repo.IRepo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private final IRepo repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    public PrgState oneStep(PrgState state) throws ControllerError {
        IStack<IStmt> stack = state.getExeStack();
        IStmt crtStmt = stack.pop();

        PrgState new_statement = null;
        try {
            new_statement = crtStmt.execute(state);
        } catch (Exception err) {
            throw new ControllerError(err.getMessage());
        }

        return new_statement;
    }

    public void allStep() {
        PrgState prg = repo.getCrtPrg();
        try {
            repo.logPrgStateExec();
            while (!prg.getExeStack().isEmpty()) {
                try {
                    PrgState new_state = oneStep(prg);
                    prg.getHeap().setContent(garbage_collector(get_used_addresses(prg.getSymTable().getContent().values(),
                            prg.getHeap().getContent().values()), prg.getHeap().getContent()));
                    repo.logPrgStateExec();
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }
            }
        } catch (IOException err) {
            System.err.println(err.getMessage());
        }
    }

    private List<Integer> get_used_addresses(Collection<IValue> symTableValues, Collection<IValue> heapTableValues) {
        List<Integer> symTableAddresses = symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {RefValue value2 = (RefValue)value;
                    return value2.getValue();})
                .collect(Collectors.toList());

        List<Integer> heapTableAddresses = heapTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(value -> {RefValue value2 = (RefValue)value;
                    return value2.getValue();})
                .collect(Collectors.toList());

        symTableAddresses.addAll(heapTableAddresses);
        return symTableAddresses;
    }

    private Map<Integer, IValue> garbage_collector(List<Integer> symTableAddr, Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
