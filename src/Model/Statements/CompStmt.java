package Model.Statements;

import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Adt.IStack;

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
    public PrgState execute(PrgState state) throws StmtError {
        IStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

    @Override
    public CompStmt deepCopy() {
        return new CompStmt(this.first, this.second);
    }
}
