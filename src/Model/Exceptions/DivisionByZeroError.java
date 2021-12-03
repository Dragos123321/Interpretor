package Model.Exceptions;

public class DivisionByZeroError extends Exception {
    public DivisionByZeroError() {
        super("Error: Division by 0.");
    }
}
