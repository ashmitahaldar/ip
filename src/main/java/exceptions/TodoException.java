package exceptions;

public class TodoException extends MayoBotException {
    public TodoException() {
        super("Input is not the correct format for the \"todo\" command.");
    }
}
