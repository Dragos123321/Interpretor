package Model.Exceptions;

import java.lang.RuntimeException;

public class StmtError extends Exception {
    public StmtError(String msg) {
        super(msg);
    }
}
