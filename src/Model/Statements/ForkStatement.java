package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.JStack;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Types.IType;

public class ForkStatement implements IStmt {
    private IStmt statement;

    public ForkStatement(IStmt stmt) {
        this.statement = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        return new PrgState(new JStack<>(), state.getSymTable().deepCopy(), state.getOutput(), state.getFileTable(), state.getHeap(), this.statement);
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStatement(statement);
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        statement.typecheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
