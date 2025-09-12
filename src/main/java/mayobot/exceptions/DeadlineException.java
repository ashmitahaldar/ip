package mayobot.exceptions;

/**
 * Exception thrown when the deadline command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for deadline tasks,
 * such as missing the "/by" keyword, empty descriptions, or missing deadline
 * specifications. The deadline command expects the format:
 * {@code deadline <description> /by <deadline>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>Missing task description before "/by"</li>
 *   <li>Missing "/by" keyword separator</li>
 *   <li>Empty or missing deadline after "/by"</li>
 *   <li>Completely empty command arguments</li>
 * </ul>
 */
public class DeadlineException extends MayoBotException {
    private static final String COMMAND_TYPE = "deadline";
    private static final String DEFAULT_HELP = "Use format: deadline <description> /by <date>";
    private static final String DATE_ERROR_PREFIX = "Date format error: ";

    /**
     * Constructs a new DeadlineException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input format is incorrect
     * for the deadline command. This constructor is used for general format
     * validation failures.
     */
    public DeadlineException() {
        super(COMMAND_TYPE, DEFAULT_HELP);
    }

    public static DeadlineException dateParsingError(String dateString) {
        return new DeadlineException(DATE_ERROR_PREFIX + "Unable to parse date '" + dateString + "'");
    }

    private DeadlineException(String message) {
        super(message);
    }
}
