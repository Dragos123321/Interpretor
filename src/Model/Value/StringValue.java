package Model.Value;

import Model.Types.IType;
import Model.Types.StringType;

import java.util.Objects;

public class StringValue implements IValue {
    private final String value;

    public StringValue() {
        this.value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        StringValue other_string = (StringValue)other;
        return Objects.equals(other_string.value, this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public boolean isRefType() {
        return false;
    }

    public String getValue() {
        return this.value;
    }

    public IValue deepCopy() {
        return new StringValue(this.value);
    }
}
