package Model.Exceptions;

import Model.Value.IValue;

import java.lang.Exception;

public class UndefinedError extends ExpError {
    public UndefinedError(String var_name) {
        super("Variable " + var_name + " is not defined.");
    }
}
