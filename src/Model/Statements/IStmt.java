package Model.Statements;

import Model.Adt.IDict;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Types.IType;

public interface IStmt {
    PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError;
    String toString();
    IStmt deepCopy();
    IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError;
}
