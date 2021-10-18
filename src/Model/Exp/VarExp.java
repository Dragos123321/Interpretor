package Model.Exp;
import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Value.IValue;

public class VarExp implements IExp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public IValue eval(IDict<String, IValue> symTable) throws ExpError {
        IValue val = symTable.lookup(id);

        if (val == null) {
            throw new ExpError("Variable " + id.toString() + " is not defined.");
        }

        return val;
    }

    public String toString() {return id;}

}
