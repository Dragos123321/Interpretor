package Model.Exp;

import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Value.IValue;

public class RelationalExp implements IExp{
    IExp e1;
    IExp e2;
    char op;

    public RelationalExp(char op, IExp first, IExp second) {
        this.op = op;
        this.e1 = first;
        this.e2 = second;
    }

    @Override
    public boolean eval(IDict<String, IValue> symTable) throws ExpError {
        return false;
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public IExp deepCopy() {
        return new RelationalExp(op, e1, e2);
    }
}
