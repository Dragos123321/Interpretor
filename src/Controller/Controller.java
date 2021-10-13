package Controller;
import Model.PrgState;
import Model.adt.IStack;
import Model.stmt.IStmt;
import Repo.IRepo;

public class Controller {
    private final IRepo repo;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    public void addProgram(PrgState newPrg) {
        repo.addPrg(newPrg);
    }

    public PrgState oneStep(PrgState state) {
        IStack<IStmt> stack = state.getExeStack();
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() {
        PrgState prg = repo.getCrtPrg();
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);
            System.out.println(prg.toString());
        }
    }
}
