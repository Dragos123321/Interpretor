package Model.Statements;

import Model.Exceptions.StmtError;
import Model.PrgState;

public class NopStmt implements IStmt {

    public PrgState execute(PrgState state) throws StmtError {
        return state;
    }

    @Override
    public String toString(){
        return "no operation";
    }

    @Override
    public NopStmt deepCopy() {
        return new NopStmt();
    }
}
