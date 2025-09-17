package mayobot.commands;

import mayobot.exceptions.MarkException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to mark a task as completed in the task list.
 * <p>
 * This command changes the completion status of a specified task from not done
 * to done. The task is identified by its 1-based index position in the task list.
 * Once marked as done, the task will display with a completion indicator.
 * <p>
 * Usage: {@code mark <task_number>}
 * <p>
 * Example: {@code mark 2} - marks the second task in the list as completed
 */
public class MarkCommand extends Command {
    private static final String MARK_SUCCESS_MESSAGE = "Nice! I've marked this task as done:";
    private static final String MARK_FAILURE_MESSAGE = "Sorry, I was not able to mark the specified task as done.";

    /**
     * Constructs a new MarkCommand with the specified arguments.
     *
     * @param arguments the 1-based index of the task to mark as done
     */
    public MarkCommand(String arguments) {
        super("mark", arguments);
    }

    /**
     * Executes the mark command to set a task's status to completed.
     * <p>
     * Validates that a task number is provided, parses it as an integer,
     * and attempts to mark the corresponding task as done. Provides appropriate
     * feedback based on whether the operation succeeds or fails.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list containing the task to mark
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message containing the marked task details
     *         for GUI mode, or error message if operation fails
     * @throws MarkException if no task number is provided or if the provided
     *                      argument is not a valid integer
     * @throws MayoBotException if an error occurs during task marking
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        boolean isSuccessful = false;
        if (arguments.trim().isEmpty()) {
            throw new MarkException();
        }

        try {
            int markIndex = Integer.parseInt(arguments);
            isSuccessful = taskList.markTaskAsDone(markIndex);
            if (isSuccessful) {
                if (!isGui) {
                    ui.showMessage(MARK_SUCCESS_MESSAGE);
                    taskList.printTask(markIndex, ui);
                }
                return buildResponse(MARK_SUCCESS_MESSAGE + "\n" + taskList.getTaskForGui(markIndex));
            } else {
                if (!isGui) {
                    ui.showMessage(MARK_FAILURE_MESSAGE);
                }
                return buildResponse(MARK_FAILURE_MESSAGE);
            }
        } catch (NumberFormatException e) {
            throw new MarkException();
        }
    }
}
