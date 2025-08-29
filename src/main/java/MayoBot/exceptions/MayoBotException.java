package MayoBot.exceptions;

public class MayoBotException extends Exception {
    public MayoBotException(String message) {
        super("☹ OOPS: " + message);
    }
}
