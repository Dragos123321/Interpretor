package Model.Value;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue {
    private final int value;

    public IntValue() {
        this.value = 0;
    }

    public IntValue(int i) {
        this.value = i;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        IntValue other_int = (IntValue)other;
        return other_int.value == this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public IType getType() {
        return new IntType();
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public IValue deepCopy() {
        return new IntValue(this.value);
    }
}
