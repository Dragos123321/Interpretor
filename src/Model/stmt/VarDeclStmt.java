package Model.stmt;


import Model.PrgState;
import Model.adt.IDict;
import Model.adt.IStack;
import Model.types.IType;
import Model.value.IValue;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) {
        IDict<String, IValue> symTable = state.getSymTable();

        if (!symTable.isDefined(name)) {
            symTable.add(name, type.defaultValue());
        }

        state.setSymTable(symTable);
        return state;
    }

    @Override
    public String toString() {
        return name.toString() + "->" + type.toString();
    }
}
