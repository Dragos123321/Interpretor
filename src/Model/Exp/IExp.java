package Model.Exp;
import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IDict<String, IValue> symTable) throws ExpError;
    String toString();
}

