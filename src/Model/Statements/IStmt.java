package Model.Statements;

import Model.Exceptions.StmtError;
import Model.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws StmtError;
    String toString();
    IStmt deepCopy();
}
