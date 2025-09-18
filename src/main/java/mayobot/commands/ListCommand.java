package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to display all tasks in the current task list.
 * <p>
 * This command shows a numbered list of all tasks currently stored in the task list,
 * including their completion status and details. The display format differs between
 * CLI and GUI modes to accommodate their respective interfaces.
 * <p>
 * Usage: {@code list}
 * <p>
 * The command requires no additional arguments and will display all tasks regardless
 * of their type (todo, deadline, or event) or completion status.
 */
public class ListCommand extends Command {
    private static final String LIST_OUTPUT_HEADER = "₊˚⊹ ♡ Here are the tasks in your list:";

    /**
     * Constructs a new ListCommand with the specified arguments.
     * <p>
     * Note: The arguments parameter is not used by this command but is
     * required to maintain consistency with the Command interface.
     *
     * @param arguments the command arguments (ignored for list command)
     */
    public ListCommand(String arguments) {
        super("list", arguments);
    }

    /**
     * Executes the list command to display all tasks in the task list.
     * <p>
     * In CLI mode, prints each task with its index number directly to the console.
     * In GUI mode, returns a formatted string containing all tasks for display
     * in the graphical interface.
     *
     * @param ui the user interface handler for displaying messages in CLI mode
     * @param taskList the task list containing all tasks to display
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted string containing all tasks for GUI mode, null for CLI mode
     * @throws MayoBotException if an error occurs during task list retrieval
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        if (!isGui) {
            ui.showMessage(LIST_OUTPUT_HEADER);
            taskList.printTasks(ui);
        }
        return super.buildResponse(LIST_OUTPUT_HEADER + "\n" + taskList.getTasksForGui());
    }
}
