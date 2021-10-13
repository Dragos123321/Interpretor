package Model.exp;

import Model.adt.IDict;
import Model.types.IntType;
import Model.value.IValue;
import Model.value.IntValue;

public class ArithExp implements IExp {
    char op;
    IExp e1, e2;

    public ArithExp(char op, IExp ex1, IExp ex2) {
        this.e1 = ex1;
        this.e2 = ex2;
        this.op = op;
    }

    public IValue eval(IDict<String, IValue> symTable) {
        IValue v1, v2;
        v1 = e1.eval(symTable);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int val1 = i1.getValue();
                int val2 = i2.getValue();
                switch (op) {
                    case '+':
                        return new IntValue(val1 + val2);
                    case '-':
                        return new IntValue(val1 - val2);
                    case '*':
                        return new IntValue(val1 * val2);
                    case '/':
                        if (val2 == 0) {
                            return new IntValue(0);
                        } else {
                            return new IntValue(val1 / val2);
                        }
                }
            } else {
                return new IntValue();
            }
        }
        return new IntValue();
    }

    public char getOp() {
        return this.op;
    }

    public IExp getFirst() {
        return this.e1;
    }

    public IExp getSecond() {
        return this.e2;
    }

    public String toString() {
        return e1.toString() + " " + op + " " + e2.toString();
    }
}
