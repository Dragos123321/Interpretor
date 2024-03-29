package Model.Statements;


import Model.Adt.IDict;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Adt.IList;
import Model.Exp.IExp;
import Model.Types.IType;
import Model.Value.IValue;

public class PrintStmt implements IStmt{

    IExp expression;

    public PrintStmt(IExp exp){
        this.expression = exp;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IList<IValue> output = state.getOutput();

        try {
            output.add(expression.eval(state.getSymTable(), state.getHeap()));
        } catch (Exception err) {
            throw new StmtError(err.getMessage());
        }

        return null;
    }

    @Override
    public PrintStmt deepCopy() {
        return new PrintStmt(this.expression);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
