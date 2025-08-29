package MayoBot.exceptions;

public class StorageException extends MayoBotException {
    public StorageException() {
        super("Ran into error when dealing with tasks.txt");
    }
}
