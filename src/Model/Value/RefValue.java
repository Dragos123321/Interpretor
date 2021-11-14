package Model.Value;

import Model.Types.IType;
import Model.Types.RefType;

public class RefValue implements IValue {
    private int address;
    private final IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public IType getLocationType() {
        return locationType;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;
        RefValue other_ref = (RefValue)other;
        return (other_ref.address == this.address && other_ref.locationType.equals(this.locationType));
    }

    @Override
    public String toString() {
        return Integer.toString(this.address) + ' ' + locationType.toString();
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public boolean isRefType() {
        return true;
    }

    public void setValue(int address) {
        this.address = address;
    }

    public int getValue() {
        return this.address;
    }

    @Override
    public IValue deepCopy() {
        return new RefValue(this.address, this.locationType);
    }
}
