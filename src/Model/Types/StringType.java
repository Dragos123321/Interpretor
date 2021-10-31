package Model.Types;

import Model.Value.IValue;
import Model.Value.StringValue;

public class StringType implements IType {
    @Override
    public IValue defaultValue() {
        return new StringValue();
    }

    @Override
    public boolean equals(Object other) {
        return other != null && this.getClass() == other.getClass();
    }

    @Override
    public IType deepCopy() {
        return new StringType();
    }

    @Override
    public String toString() {
        return "string";
    }
}
