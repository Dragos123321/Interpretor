package Model.Statements;

import Model.Adt.IDict;
import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Types.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws StmtError;
    String toString();
    IStmt deepCopy();
    IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws Exception;
}
