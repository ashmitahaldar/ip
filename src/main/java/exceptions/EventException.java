package exceptions;

public class EventException extends MayoBotException {
    public EventException() {
        super("Input is not the correct format for the \"event\" command.");
    }
}
