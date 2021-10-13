package Model.exp;
import Model.adt.IDict;
import Model.value.IValue;

public class VarExp implements IExp{
    String id;

    public VarExp(String id){
        this.id = id;
    }

    public IValue eval(IDict<String, IValue> symTable) {
        return symTable.lookup(id);
    }

    public String toString() {return id;}

}
