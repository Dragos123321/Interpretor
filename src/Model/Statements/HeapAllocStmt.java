package Model.Statements;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Exceptions.StmtError;
import Model.Exp.IExp;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
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
    public PrgState execute(PrgState state) throws StmtError {
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
                        throw new StmtError("Type mismatch.");
                    }
                }
                catch(ExpError err) {
                    throw new StmtError(err.getMessage());
                }
            }
            else {
                throw new StmtError(var_name + " is not a reference type.");
            }
        }
        else {
            throw new StmtError(var_name + " is not defined.");
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new HeapAllocStmt(var_name, exp);
    }

    @Override
    public String toString() {
        return var_name + " -> (" + exp.toString() + ")";
    }
}
