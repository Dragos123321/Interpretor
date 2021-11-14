package Model.Exp;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Value.IValue;
import Model.Value.RefValue;

public class HeapReadingExp implements IExp {
    IExp exp;

    public HeapReadingExp(IExp exp) {
        this.exp = exp;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError {
        IValue val;
        val = this.exp.eval(symTable, heap);
        if (val.isRefType()) {
            RefValue r_val = (RefValue) val;
            int address = r_val.getValue();
            if (heap.isDefined(address)) {
                return heap.lookup(address);
            }
            else {
                throw new ExpError("Memory not allocated at: " + address + ".");
            }
        }
        else {
            throw new ExpError("Expression is not a reference value.");
        }
    }

    @Override
    public IExp deepCopy() {
        return new HeapReadingExp(exp);
    }

    @Override
    public String toString() {
        return exp.toString();
    }
}
