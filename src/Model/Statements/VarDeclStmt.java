package Model.Statements;


import Model.Exceptions.*;
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
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IDict<String, IValue> symTable = state.getSymTable();

        if (!symTable.isDefined(name)) {
            symTable.add(name, type.defaultValue());
        } else {
            throw new StmtError("Variable " + name + " already declared.");
        }

        return null;
    }

    @Override
    public String toString() {
        return name.toString() + "->" + type.toString();
    }

    @Override
    public VarDeclStmt deepCopy() {
        return new VarDeclStmt(this.name, this.type);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        typeEnv.add(name, type);
        return typeEnv;
    }
}
