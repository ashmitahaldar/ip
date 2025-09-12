package mayobot.exceptions;

/**
 * Exception thrown when the todo command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for todo tasks,
 * specifically when the task description is missing or empty. The todo command
 * expects a non-empty description: {@code todo <description>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>Empty command arguments (just typing "todo")</li>
 *   <li>Whitespace-only description</li>
 *   <li>Missing task description after the "todo" keyword</li>
 * </ul>
 * <p>
 * Todo tasks are the simplest task type in MayoBot, requiring only a
 * description without any time constraints or additional formatting.
 * This exception ensures users provide meaningful task descriptions.
 */
public class TodoException extends MayoBotException {
    private static final String COMMAND_TYPE = "todo";
    private static final String HELP_MESSAGE = "Please provide a task description.";

    /**
     * Constructs a new TodoException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input format is incorrect
     * for the todo command. This constructor is used when the user fails to
     * provide a valid task description.
     */
    public TodoException() {
        super(COMMAND_TYPE, HELP_MESSAGE);
    }

    public TodoException(String customMessage) {
        super(customMessage);
    }
}
