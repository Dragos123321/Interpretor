package Model.exp;
import Model.adt.IDict;
import Model.value.IValue;

public interface IExp {
    IValue eval(IDict<String, IValue> symTable);
    String toString();
}

