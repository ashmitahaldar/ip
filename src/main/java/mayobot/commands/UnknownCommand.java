package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.UnknownCommandException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Represents an unrecognized or invalid command entered by the user.
 * <p>
 * This command is created when the parser encounters input that doesn't match
 * any of the known command types (todo, deadline, event, list, mark, unmark,
 * delete, find, bye). When executed, it throws an exception to inform the user
 * that the command is not recognized.
 * <p>
 * This class serves as a fallback mechanism to handle invalid user input
 * gracefully rather than causing the application to crash or behave unexpectedly.
 */
public class UnknownCommand extends Command {
    /**
     * Constructs a new UnknownCommand with the specified command and arguments.
     * <p>
     * Unlike other command types, this constructor accepts the actual command
     * string that was not recognized, allowing for more specific error reporting.
     *
     * @param command the unrecognized command string that was entered
     * @param arguments the arguments that were provided with the unknown command
     */
    public UnknownCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes the unknown command by throwing an UnknownCommandException.
     * <p>
     * This method always throws an exception and never performs any actual
     * operations on the task list or UI. The exception contains information
     * about the unrecognized command to help the user understand what went wrong.
     *
     * @param ui the user interface handler (not used)
     * @param taskList the task list (not modified)
     * @param isGui true if running in GUI mode, false for CLI mode (not used)
     * @return never returns normally, always throws an exception
     * @throws UnknownCommandException always thrown to indicate the command
     *                                is not recognized, includes the command string
     * @throws MayoBotException parent exception type for all bot-related errors
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        throw new UnknownCommandException(this.getCommand());
    }
}
