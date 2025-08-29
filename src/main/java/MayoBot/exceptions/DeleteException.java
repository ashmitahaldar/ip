package MayoBot.exceptions;

public class DeleteException extends MayoBotException{
    public DeleteException() {
        super("Input is incorrect for the \"delete\" command.");
    }
}
