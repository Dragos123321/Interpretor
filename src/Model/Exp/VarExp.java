package Model.Exp;
import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Exceptions.UndefinedError;
import Model.Types.IType;
import Model.Value.IValue;

public class VarExp implements IExp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError {
        IValue val = symTable.lookup(id);

        if (val == null) {
            throw new UndefinedError(id);
        }

        return val;
    }

    public String toString() {return id;}

    @Override
    public VarExp deepCopy() {
        return new VarExp(this.id);
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws Exception {
        return typeEnv.lookup(id);
    }
}
