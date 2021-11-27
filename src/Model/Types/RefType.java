package Model.Types;

import Model.Value.IValue;
import Model.Value.RefValue;

public class RefType implements IType{
    IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    public IType getInner() {
        return this.inner;
    }

    @Override
    public IValue defaultValue() {
        return new RefValue(0, this.inner);
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == this.getClass() && ((RefType) other).inner.equals(this.inner);
    }

    @Override
    public IType deepCopy() {
        return new RefType(this.inner);
    }

    @Override
    public String toString(){
        return "Ref(" + inner.toString() + ")";
    }
}
