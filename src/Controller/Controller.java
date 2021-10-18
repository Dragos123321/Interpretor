package Controller;
import Model.Exceptions.ControllerError;
import Model.PrgState;
import Model.Adt.IStack;
import Model.Statements.IStmt;
import Repo.IRepo;

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

        try {
            return crtStmt.execute(state);
        } catch (Exception err) {
            throw new ControllerError(err.toString());
        }
    }

    public void allStep() {
        PrgState prg = repo.getCrtPrg();
        while (!prg.getExeStack().isEmpty()) {
            try {
                oneStep(prg);
                System.out.println(prg.toString());
            } catch (Exception err) {
                System.err.println(err.toString());
            }
        }
    }
}
