package Model.Statements;

import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Adt.IDict;
import Model.Adt.IStack;
import Model.Exp.IExp;
import Model.Types.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class IfStmt implements IStmt {
    IExp exp;
    IStmt first;
    IStmt second;

    public IfStmt(IExp ex, IStmt first, IStmt second) {
        this.exp = ex;
        this.first = first;
        this.second = second;
    }

    public PrgState execute(PrgState state) throws StmtError {
        IStack<IStmt> stack = state.getExeStack();
        IDict<String, IValue> symTable = state.getSymTable();

        try {
            IValue cond = exp.eval(symTable);
            if (!cond.getType().equals(new BoolType())) {
                throw new StmtError("Conditional expr is not a boolean.");
            } else {
                BoolValue cond_bool = (BoolValue) cond;
                if (cond_bool.getValue()) {
                    stack.push(first);
                } else {
                    stack.push(second);
                }
            }

            state.setExeStack(stack);
        } catch (Exception err) {
            throw new StmtError(err.toString());
        }

        return state;
    }
}
