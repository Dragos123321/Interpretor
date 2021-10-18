package Model.Value;

import Model.Types.BoolType;
import Model.Types.IType;

public class BoolValue implements IValue{
    private final boolean value;

    public BoolValue() {
        this.value = false;
    }

    public BoolValue(boolean i){
        this.value = i;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != this.getClass())
            return false;
        BoolValue other_bool = (BoolValue)other;
        return other_bool.value == this.value;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.value ? "true" : "false";
    }

    @Override
    public IType getType() {
        return new BoolType();
    }

    @Override
    public IValue deepCopy() {
        return new BoolValue(this.value);
    }
}
