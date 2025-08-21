package exceptions;

public class UnmarkException extends MayoBotException {
    public UnmarkException() {
        super("Input is not the correct format for the \"unmark\" command.");
    }
}
