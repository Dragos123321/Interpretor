package Model.Exp;

import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Value.BoolValue;
import Model.Value.IValue;
import Model.Value.IntValue;

public class LogicExp implements IExp {
    char op;
    IExp e1, e2;

    public LogicExp(char op, IExp ex1, IExp ex2) {
        this.e1 = ex1;
        this.e2 = ex2;
        this.op = op;
    }

    public IValue eval(IDict<String, IValue> symTable) throws ExpError {
        IValue v1, v2;
        v1 = e1.eval(symTable);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(symTable);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue) v1;
                BoolValue b2 = (BoolValue) v2;
                boolean val1 = b1.getValue();
                boolean val2 = b2.getValue();
                switch (op) {
                    case '&':
                        return new BoolValue(val1 & val2);
                    case '|':
                        return new BoolValue(val1 | val2);
                    case '^':
                        return new BoolValue(val1 ^ val2);
                }
            } else {
                throw new ExpError("Operand 2 is not a boolean.");
            }
        }

        throw new ExpError("Operand 1 is not a boolean.");
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