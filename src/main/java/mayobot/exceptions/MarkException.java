package mayobot.exceptions;

/**
 * Exception thrown when the mark command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for the mark command,
 * which is used to mark tasks as completed. The mark command expects a valid
 * task number as its argument: {@code mark <task_number>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>No task number provided (empty arguments)</li>
 *   <li>Non-numeric input provided instead of a task number</li>
 *   <li>Whitespace-only input</li>
 * </ul>
 */
public class MarkException extends MayoBotException {
    private static final String COMMAND_TYPE = "mark";
    private static final String DEFAULT_HELP = "Use format: mark <task number>";
    /**
     * Constructs a new MarkException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input format is incorrect
     * for the mark command. This typically occurs when the user fails to provide
     * a valid task number.
     */
    public MarkException() {
        super(COMMAND_TYPE, DEFAULT_HELP);
    }

    public MarkException(String message) {
        super(message);
    }
}
