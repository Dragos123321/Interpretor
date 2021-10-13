package Model.stmt;


import Model.PrgState;
import Model.adt.IList;
import Model.exp.IExp;
import Model.value.IValue;

public class PrintStmt implements IStmt{

    IExp expression;

    public PrintStmt(IExp exp){
        this.expression = exp;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public PrgState execute(PrgState state){
        IList<IValue> output = state.getOutput();
        output.add(expression.eval(state.getSymTable()));
        state.setOutput(output);
        return state;
    }
}
