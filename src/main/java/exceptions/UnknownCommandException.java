package exceptions;

public class UnknownCommandException extends MayoBotException {
    public UnknownCommandException(String message) {
        super("Input is not a valid command: " + message);
    }
}
