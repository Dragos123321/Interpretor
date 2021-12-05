package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.*;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.*;
import Model.Value.IValue;
import Model.Value.RefValue;

public class HeapAllocStmt implements IStmt {
    private final String var_name;
    private final IExp exp;

    public HeapAllocStmt(String var_name, IExp exp) {
        this.var_name = var_name;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws StmtError, TypeMismatch, DivisionByZeroError, NotRefError, UndefinedVariable, FileNotOpenedError, InvalidMemoryAddressError {
        IDict<String, IValue> symTable = state.getSymTable();
        IHeap<IValue> heap = state.getHeap();

        if (symTable.isDefined(var_name)) {
            IValue val1 = symTable.lookup(var_name);
            if (val1.isRefType()) {
                RefValue r_val1 = (RefValue) val1;
                try {
                    IValue val2 = this.exp.eval(symTable, heap);
                    if (val2.getType().equals(r_val1.getLocationType())) {
                        r_val1.setValue(heap.add(val2));
                    }
                    else {
                        throw new TypeMismatch("Type mismatch.");
                    }
                }
                catch(ExpError err) {
                    throw new StmtError(err.getMessage());
                }
            }
            else {
                throw new NotRefError();
            }
        }
        else {
            throw new UndefinedVariable(var_name + " is not defined.");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(var_name, exp);
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws StmtError, TypeMismatch, NotRefError {
        IType type_var = typeEnv.lookup(var_name);
        IType type_exp = exp.typeCheck(typeEnv);

        if (type_var.equals(new RefType(type_exp)))
            return typeEnv;
        else
            throw new TypeMismatch("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" + var_name + ", " + exp.toString() + ")";
    }
}
