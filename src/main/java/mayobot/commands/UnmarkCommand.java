package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.UnmarkException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to mark a task as not completed in the task list.
 * <p>
 * This command changes the completion status of a specified task from done
 * back to not done. The task is identified by its 1-based index position
 * in the task list. This is useful for reopening completed tasks that need
 * additional work or were marked as done by mistake.
 * <p>
 * Usage: {@code unmark <task_number>}
 * <p>
 * Example: {@code unmark 3} - marks the third task in the list as not completed
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_SUCCESS_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String UNMARK_FAILURE_MESSAGE = "Sorry, I was not able to mark the specified task as "
                                                        + "not done yet.";

    /**
     * Constructs a new UnmarkCommand with the specified arguments.
     *
     * @param arguments the 1-based index of the task to mark as not done
     */
    public UnmarkCommand(String arguments) {
        super("unmark", arguments);
    }

    /**
     * Executes the unmark command to set a task's status to not completed.
     * <p>
     * Validates that a task number is provided, parses it as an integer,
     * and attempts to mark the corresponding task as not done. Provides
     * appropriate feedback based on whether the operation succeeds or fails.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list containing the task to unmark
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message containing the unmarked task details
     *         for GUI mode, or error message if operation fails
     * @throws UnmarkException if no task number is provided or if the provided
     *                        argument is not a valid integer
     * @throws MayoBotException if an error occurs during task unmarking
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        boolean success = false;
        if (arguments.trim().isEmpty()) {
            throw new UnmarkException();
        }

        try {
            int unmarkIndex = Integer.parseInt(arguments);
            success = taskList.markTaskAsNotDone(unmarkIndex);
            if (success) {
                if (!isGui) {
                    ui.showMessage(UNMARK_SUCCESS_MESSAGE);
                    taskList.printTask(unmarkIndex, ui);
                }
                return buildResponse(UNMARK_SUCCESS_MESSAGE + "\n" + taskList.getTaskForGui(unmarkIndex));
            } else {
                if (!isGui) {
                    ui.showMessage(UNMARK_FAILURE_MESSAGE);
                }
                return buildResponse(UNMARK_FAILURE_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new UnmarkException();
        }
    }
}
