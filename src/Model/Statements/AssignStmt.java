package Model.Statements;

import Model.Adt.IHeap;
import Model.Exceptions.*;
import Model.PrgState;
import Model.Adt.IDict;
import Model.Exp.IExp;
import Model.Types.IType;
import Model.Value.IValue;

public class AssignStmt implements IStmt{

    String id;
    IExp expression;

    public AssignStmt(String id, IExp exp){
        this.id = id;
        this.expression = exp;
    }

    @Override
    public String toString(){
        return this.id + "=" + this.expression.toString();
    }

    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if (symTable.isDefined(id)) {
            try {
                IValue val = expression.eval(symTable, heap);
                IType type = symTable.lookup(id).getType();
                if (val.getType().equals(type)) {
                    symTable.update(id, val);
                } else {
                    throw new TypeMismatch("Type of expression and type of variable do not match.");
                }
            } catch (Exception err) {
                throw new StmtError(err.getMessage());
            }
        } else {
            throw new UndefinedVariable("Variable " + id.toString() + " is not defined.");
        }

        return null;
    }

    @Override
    public AssignStmt deepCopy() {
        return new AssignStmt(this.id, this.expression);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        IType type_var = typeEnv.lookup(id);
        IType type_exp = expression.typeCheck(typeEnv);
        if (type_var.equals(type_exp)) {
            return typeEnv;
        } else {
            throw new TypeMismatch("Assignment: right hand side and left hand side have different types.");
        }
    }
}
