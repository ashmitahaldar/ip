package exceptions;

public class DeadlineException extends MayoBotException {
    public DeadlineException() {
        super("Input is not the correct format for the \"deadline\" command.");
    }
}
