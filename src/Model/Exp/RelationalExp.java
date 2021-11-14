package Model.Exp;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.ExpError;
import Model.Types.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class RelationalExp implements IExp {
    IExp e1;
    IExp e2;
    String op;

    public RelationalExp(String op, IExp first, IExp second) {
        this.op = op;
        this.e1 = first;
        this.e2 = second;
    }

    @Override
    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError {
        IValue v1, v2;
        v1 = this.e1.eval(symTable, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = this.e2.eval(symTable, heap);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int val1 = i1.getValue();
                int val2 = i2.getValue();
                switch (op) {
                    case "<":
                        return new BoolValue(val1 < val2);
                    case "<=":
                        return new BoolValue(val1 <= val2);
                    case ">":
                        return new BoolValue(val1 > val2);
                    case ">=":
                        return new BoolValue(val1 >= val2);
                    case "==":
                        return new BoolValue(val1 == val2);
                    case "!=":
                        return new BoolValue(val1 != val2);
                }
            } else {
                throw new ExpError("Operand 2 is not an integer.");
            }
        }
        throw new ExpError("Operand 1 is not an integer.");
    }

    @Override
    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }

    @Override
    public IExp deepCopy() {
        return new RelationalExp(op, e1, e2);
    }
}
