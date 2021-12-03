package Model.Exceptions;

public class NotRefError extends Exception{
    public NotRefError() {
        super("Expression is not a reference value");
    }
}
