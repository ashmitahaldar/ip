package mayobot.exceptions;

public class MarkException extends MayoBotException {
    public MarkException() {
        super("Input is not the correct format for the \"mark\" command.");
    }
}
