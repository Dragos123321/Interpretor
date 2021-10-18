package Model.Exp;

import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Value.IValue;

public class ValueExp implements IExp{
    IValue elem;

    public ValueExp(IValue el) {
        this.elem = el;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable) throws ExpError {
        return elem;
    }

    @Override
    public String toString() {
        return elem.toString();
    }
}
