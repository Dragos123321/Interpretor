package Model.stmt;

import Model.PrgState;
import Model.adt.IStack;

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
    public PrgState execute(PrgState state) {
        IStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        state.setExeStack(stack);
        return state;
    }
}
