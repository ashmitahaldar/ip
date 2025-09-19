package mayobot.exceptions;

/**
 * Exception thrown when the unmark command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for the unmark command,
 * which is used to mark completed tasks as not done. The unmark command expects
 * a valid task number as its argument: {@code unmark <task_number>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>No task number provided (empty arguments)</li>
 *   <li>Non-numeric input provided instead of a task number</li>
 *   <li>Whitespace-only input</li>
 * </ul>
 * <p>
 * The unmark command is the complement to the mark command, allowing users
 * to reopen tasks that were previously marked as completed. This is useful
 * when tasks were marked as done by mistake or require additional work.
 */
public class UnmarkException extends MayoBotException {
    private static final String COMMAND_TYPE = "unmark";
    private static final String DEFAULT_HELP = "Use format: unmark <task number>";

    /**
     * Constructs a new UnmarkException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input format is incorrect
     * for the unmark command. This typically occurs when the user fails to
     * provide a valid task number to unmark.
     */
    public UnmarkException() {
        super(COMMAND_TYPE, DEFAULT_HELP);
    }

    public UnmarkException(String message) {
        super(message);
    }
}
