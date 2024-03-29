package Model.Statements;

import Model.Adt.IDict;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Adt.IStack;
import Model.Types.IType;

public class CompStmt implements IStmt {
    IStmt first;
    IStmt second;

    public CompStmt(IStmt first, IStmt second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "; " + second.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public CompStmt deepCopy() {
        return new CompStmt(this.first, this.second);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        return second.typecheck(first.typecheck(typeEnv));
    }
}
