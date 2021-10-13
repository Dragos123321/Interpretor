package Model.exp;

import Model.adt.IDict;
import Model.value.IValue;

public class ValueExp implements IExp{
    IValue elem;

    public ValueExp(IValue el) {
        this.elem = el;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable) {
        return elem;
    }

    @Override
    public String toString() {
        return elem.toString();
    }
}
