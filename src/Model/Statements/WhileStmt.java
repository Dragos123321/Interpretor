package Model.Statements;

import Model.Adt.IStack;
import Model.Exceptions.ExpError;
import Model.Exceptions.StmtError;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Value.BoolValue;
import Model.Value.IValue;

public class WhileStmt implements IStmt {
    IExp exp;
    IStmt statement;

    public WhileStmt(IExp exp, IStmt statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError {
        IStack<IStmt> stack = state.getExeStack();

        try {
            IValue val = this.exp.eval(state.getSymTable(), state.getHeap());
            if (val.getType().equals(new BoolType())) {
                BoolValue b_val = (BoolValue) val;
                if (b_val.getValue()) {
                    stack.push(this);
                    stack.push(this.statement);
                }
            }
            else {
                throw new StmtError(exp.toString() + " is not evaluated to boolean");
            }
        }
        catch(ExpError err) {
            throw new StmtError(err.getMessage());
        }

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(this.exp, this.statement);
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + ") " + statement.toString();
    }
}
