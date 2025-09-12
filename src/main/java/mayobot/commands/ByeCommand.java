package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to terminate the MayoBot application.
 * <p>
 * This command handles user requests to exit the application gracefully.
 * When executed, it sets the exit flag to true, signaling the main application
 * loop to terminate.
 * <p>
 * The command does not modify the task list or produce any output messages,
 * as its sole purpose is to initiate application shutdown.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a new ByeCommand with the specified arguments.
     * <p>
     * Note: The arguments parameter is not used by this command but is
     * required to maintain consistency with the Command interface.
     *
     * @param arguments the command arguments (ignored for bye command)
     */
    public ByeCommand(String arguments) {
        super("bye", arguments);
    }

    /**
     * Executes the bye command to terminate the application.
     * <p>
     * Sets the exit flag to true, indicating that the application should
     * terminate after this command completes. No task list modifications
     * or user interface updates are performed.
     *
     * @param ui the user interface handler (not used)
     * @param taskList the task list (not modified)
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return null as no response message is generated
     * @throws MayoBotException never thrown by this command
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        this.isExit = true;
        return null;
    }
}
