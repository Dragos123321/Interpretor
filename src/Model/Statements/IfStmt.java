package Model.Statements;

import Model.Adt.IHeap;
import Model.Exceptions.NotRefError;
import Model.Exceptions.StmtError;
import Model.Exceptions.TypeMismatch;
import Model.PrgState;
import Model.Adt.IDict;
import Model.Adt.IStack;
import Model.Exp.IExp;
import Model.Types.BoolType;
import Model.Types.IType;
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
        IHeap<IValue> heap = state.getHeap();

        try {
            IValue cond = exp.eval(symTable, heap);
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
        } catch (Exception err) {
            throw new StmtError(err.getMessage());
        }

        return null;
    }

    @Override
    public String toString() {
        return "if (" + exp.toString() + ") " + first.toString() + " else " + second.toString();
    }

    @Override
    public IfStmt deepCopy() {
        return new IfStmt(this.exp, this.first, this.second);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        IType type_exp = exp.typeCheck(typeEnv);
        if (type_exp.equals(new BoolType())) {
            first.typecheck(typeEnv.deepCopy());
            second.typecheck(typeEnv.deepCopy());
            return typeEnv;
        } else {
            throw new TypeMismatch("The condition of IF is not of type boolean");
        }
    }
}
