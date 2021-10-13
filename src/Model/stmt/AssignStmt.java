package Model.stmt;

import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.exp.IExp;
import Model.types.IType;
import Model.value.IValue;

public class AssignStmt implements IStmt{

    String id;
    IExp expression;

    public AssignStmt(String id, IExp exp){
        this.id = id;
        this.expression = exp;
    }

    @Override
    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    public PrgState execute(PrgState state){
        IDict<String, IValue> symTable = state.getSymTable();

        if (symTable.isDefined(id)) {
            IValue val = expression.eval(symTable);
            IType type = symTable.lookup(id).getType();
            if (val.getType().equals(type)) {
                symTable.update(id, val);
            }
        }

        state.setSymTable(symTable);
        return state;
    }
}
