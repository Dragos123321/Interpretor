package Model.Exp;
import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.DivisionByZeroError;
import Model.Exceptions.ExpError;
import Model.Exceptions.NotRefError;
import Model.Exceptions.TypeMismatch;
import Model.Types.IType;
import Model.Value.IValue;

public interface IExp {
    IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError, TypeMismatch, DivisionByZeroError, NotRefError;
    String toString();
    IExp deepCopy();
    IType typeCheck(IDict<String, IType> typeEnv) throws TypeMismatch, NotRefError;
}

