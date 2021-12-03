package Model.Exp;

import Model.Adt.IDict;
import Model.Adt.IHeap;
import Model.Exceptions.DivisionByZeroError;
import Model.Exceptions.ExpError;
import Model.Exceptions.NotRefError;
import Model.Exceptions.TypeMismatch;
import Model.Types.IType;
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

    public IValue eval(IDict<String, IValue> symTable, IHeap<IValue> heap) throws ExpError, TypeMismatch, DivisionByZeroError, NotRefError {
        IValue v1, v2;
        v1 = e1.eval(symTable, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(symTable, heap);
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
                            throw new DivisionByZeroError();
                        } else {
                            return new IntValue(val1 / val2);
                        }
                }
            } else {
                throw new TypeMismatch("Operand 2 is not an integer.");
            }
        }

        throw new TypeMismatch("Operand 1 is not an integer.");
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

    @Override
    public ArithmeticExp deepCopy() {
        return new ArithmeticExp(this.op, this.e1, this.e2);
    }

    @Override
    public IType typeCheck(IDict<String, IType> typeEnv) throws TypeMismatch, NotRefError {
        IType type1, type2;
        type1 = e1.typeCheck(typeEnv);
        type2 = e2.typeCheck(typeEnv);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            }
            else {
                throw new TypeMismatch("Operand 2 is not an integer.");
            }
        } else {
            throw new TypeMismatch("Operand 1 is not an integer.");
        }
    }
}
