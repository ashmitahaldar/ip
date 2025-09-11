package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.TodoException;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.task.TodoTask;
import mayobot.ui.Ui;

/**
 * Command to create and add a new todo task to the task list.
 * <p>
 * This command handles the creation of simple todo tasks that contain
 * only a description without any time constraints or deadlines.
 * <p>
 * Usage: {@code todo <description>}
 * <p>
 * Example: {@code todo Buy groceries}
 */
public class TodoCommand extends Command {
    /**
     * Constructs a new TodoCommand with the specified arguments.
     *
     * @param arguments the todo task description
     */
    public TodoCommand(String arguments) {
        super("todo", arguments);
    }

    /**
     * Executes the todo command to create and add a new todo task.
     * <p>
     * Validates that a description is provided, creates a new TodoTask,
     * and adds it to the task list. In CLI mode, displays a confirmation
     * message to the user.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list to add the new todo task to
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message for GUI mode, null for CLI mode
     * @throws TodoException if no description is provided (empty or whitespace-only)
     * @throws MayoBotException if an error occurs during task creation
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            throw new TodoException();
        }
        Task newTodoTask = new TodoTask(arguments);
        String addTodoTaskMessage = taskList.addTask(newTodoTask, ui, isGui);
        if (!isGui) {
            ui.showMessage(addTodoTaskMessage);
            return null;
        }
        return buildResponse(addTodoTaskMessage);
    }
}
