package Model.Statements;


import Model.Exceptions.StmtError;
import Model.PrgState;
import Model.Adt.IDict;
import Model.Types.IType;
import Model.Value.IValue;

public class VarDeclStmt implements IStmt{
    String name;
    IType type;

    public VarDeclStmt(String name, IType type){
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError {
        IDict<String, IValue> symTable = state.getSymTable();

        if (!symTable.isDefined(name)) {
            symTable.add(name, type.defaultValue());
        } else {
            throw new StmtError("Variable " + name + " already declared.");
        }

        state.setSymTable(symTable);
        return state;
    }

    @Override
    public String toString() {
        return name.toString() + "->" + type.toString();
    }
}
