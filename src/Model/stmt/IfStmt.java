package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.IExp;
import Model.types.BoolType;
import Model.value.BoolValue;
import Model.value.IValue;

public class IfStmt implements IStmt {
    IExp exp;
    IStmt first;
    IStmt second;

    public IfStmt(IExp ex, IStmt first, IStmt second) {
        this.exp = ex;
        this.first = first;
        this.second = second;
    }

    public PrgState execute(PrgState state) {
        IStack<IStmt> stack = state.getExeStack();
        IDict<String, IValue> symTable = state.getSymTable();

        IValue cond = exp.eval(symTable);
        if (!cond.getType().equals(new BoolType())) {
            return state;
        } else {
            BoolValue cond_bool = (BoolValue) cond;
            if (cond_bool.getValue()) {
                stack.push(first);
            } else {
                stack.push(second);
            }
        }

        state.setExeStack(stack);

        return state;
    }
}
