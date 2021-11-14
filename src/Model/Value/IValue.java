package Model.Value;

import Model.Types.IType;

public interface IValue {
    IType getType();
    boolean isRefType();
    IValue deepCopy();
}
