package Model.Exp;
import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError;
    String toString();
    IExp deepCopy();
}

