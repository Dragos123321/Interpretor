package Model.Statements;

import Model.Adt.IDict;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Types.IType;

public class NopStmt implements IStmt {

    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
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

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        return typeEnv;
    }
}
