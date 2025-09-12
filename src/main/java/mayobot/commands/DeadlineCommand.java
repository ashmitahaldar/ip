package mayobot.commands;

import mayobot.exceptions.DeadlineException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.DeadlineTask;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to create and add a new deadline task to the task list.
 * <p>
 * This command handles the creation of tasks that have a specific deadline
 * or due date. The deadline must be specified using the "/by" keyword.
 * <p>
 * Usage: {@code deadline <description> /by <deadline>}
 * <p>
 * Example: {@code deadline Submit assignment /by 2024-12-25 23:59}
 */
public class DeadlineCommand extends Command {
    private static final String BY_SEPARATOR = " /by";
    private static final int EXPECTED_PARTS = 2;

    /**
     * Constructs a new DeadlineCommand with the specified arguments.
     *
     * @param arguments the deadline task description and deadline specification
     */
    public DeadlineCommand(String arguments) {
        super("deadline", arguments);
    }

    /**
     * Executes the deadline command to create and add a new deadline task.
     * <p>
     * Parses the arguments to extract the task description and deadline,
     * validates the input format, creates a new DeadlineTask, and adds it
     * to the task list. Handles date format errors gracefully.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list to add the new deadline task to
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message for GUI mode, null for CLI mode
     * @throws DeadlineException if the input format is invalid (missing description,
     *                          missing "/by" keyword, or empty deadline)
     * @throws MayoBotException if a date format error occurs during task creation
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        DeadlineTaskComponents components = parseDeadlineArguments(arguments);

        try {
            Task newDeadlineTask = new DeadlineTask(components.description, components.by);
            return handleTaskCreation(newDeadlineTask, taskList, ui, isGui);
        } catch (IllegalArgumentException e) {
            // TODO: Use DeadlineException instead.
            throw new MayoBotException(DATE_FORMAT_ERROR_PREFIX + e.getMessage());
        }
    }

    private boolean isValidDeadlineParts(String[] parts) {
        return parts.length == EXPECTED_PARTS
                && !parts[0].trim().isEmpty()
                && !parts[1].trim().isEmpty();
    }

    private DeadlineTaskComponents parseDeadlineArguments(String arguments) throws DeadlineException {
        String[] deadlineParts = arguments.split(BY_SEPARATOR, EXPECTED_PARTS);
        if (!isValidDeadlineParts(deadlineParts)) {
            throw new DeadlineException();
        }

        String deadlineDescription = deadlineParts[0];
        String by = deadlineParts[1];

        return new DeadlineTaskComponents(deadlineDescription, by);
    }

    private record DeadlineTaskComponents(String description, String by) {}
}
