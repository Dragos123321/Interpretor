package Model.Exceptions;

import java.lang.Exception;

public class ControllerError extends Exception {
    public ControllerError(String msg) {
        super(msg);
    }
}
