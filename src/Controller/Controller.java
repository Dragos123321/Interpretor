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
        while (!prg.getExeStack().isEmpty()) {
            try {
                PrgState new_state = oneStep(prg);
                System.out.println(new_state.toString());
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
    }
}
