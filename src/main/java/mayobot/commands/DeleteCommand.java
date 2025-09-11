package mayobot.commands;

import mayobot.exceptions.DeleteException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to delete a task from the task list.
 * <p>
 * This command removes a task at the specified index from the task list.
 * The index is 1-based to match user expectations, where the first task
 * is referred to as task 1, not task 0.
 * <p>
 * Usage: {@code delete <task_number>}
 * <p>
 * Example: {@code delete 3} - deletes the third task from the list
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a new DeleteCommand with the specified arguments.
     *
     * @param arguments the 1-based index of the task to delete
     */
    public DeleteCommand(String arguments) {
        super("delete", arguments);
    }

    /**
     * Executes the delete command to remove a task from the task list.
     * <p>
     * Validates that a task number is provided, parses it as an integer,
     * and removes the corresponding task from the list. Handles various
     * error conditions including invalid numbers and out-of-bounds indices.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list to delete the task from
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message for GUI mode, null for CLI mode
     * @throws DeleteException if no task number is provided or if the task
     *                        index is out of bounds
     * @throws MayoBotException if an error occurs during task deletion
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            throw new DeleteException();
        }

        try {
            int deleteIndex = Integer.parseInt(arguments);
            String deleteTaskMessage = taskList.deleteTask(deleteIndex, ui, isGui);
            if (!isGui) {
                ui.showMessage(deleteTaskMessage);
            }
            return buildResponse(deleteTaskMessage);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DeleteException();
        }
    }
}
