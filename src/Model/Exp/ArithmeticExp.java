package Model.Exp;

import Model.Adt.IDict;
import Model.Exceptions.ExpError;
import Model.Types.IntType;
import Model.Value.IValue;
import Model.Value.IntValue;

public class ArithmeticExp implements IExp {
    char op;
    IExp e1, e2;

    public ArithmeticExp(char op, IExp ex1, IExp ex2) {
        this.e1 = ex1;
        this.e2 = ex2;
        this.op = op;
    }

    public IValue eval(IDict<String, IValue> symTable) throws ExpError {
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
                            throw new ExpError("Division by 0.");
                        } else {
                            return new IntValue(val1 / val2);
                        }
                }
            } else {
                throw new ExpError("Operand 2 is not an integer.");
            }
        }

        throw new ExpError("Operand 1 is not an integer.");
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
