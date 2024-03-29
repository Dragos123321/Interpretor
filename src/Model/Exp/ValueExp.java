package Model.Exp;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.DivisionByZeroError;
import Model.Exceptions.ExpError;
import Model.Exceptions.NotRefError;
import Model.Exceptions.TypeMismatch;
import Model.Types.IType;
import Model.Value.IValue;

public class ValueExp implements IExp{
    IValue elem;

    public ValueExp(IValue el) {
        this.elem = el;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError, TypeMismatch, DivisionByZeroError, NotRefError {
        return elem;
    }

    @Override
    public String toString() {
        return elem.toString();
    }

    @Override
    public ValueExp deepCopy() {
        return new ValueExp(this.elem);
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws TypeMismatch, NotRefError {
        return elem.getType();
    }
}
