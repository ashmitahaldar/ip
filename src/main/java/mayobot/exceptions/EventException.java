package mayobot.exceptions;

/**
 * Exception thrown when the event command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for event tasks,
 * such as missing "/from" or "/to" keywords, empty descriptions, or missing
 * time specifications. The event command expects the format:
 * {@code event <description> /from <start_time> /to <end_time>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>Missing event description before "/from"</li>
 *   <li>Missing "/from" keyword separator</li>
 *   <li>Missing "/to" keyword separator</li>
 *   <li>Empty or missing start time after "/from"</li>
 *   <li>Empty or missing end time after "/to"</li>
 *   <li>Completely empty command arguments</li>
 * </ul>
 */
public class EventException extends MayoBotException {
    private static final String COMMAND_TYPE = "event";
    private static final String DEFAULT_HELP = "Use format: event <description> /from <start> /to <end>";

    /**
     * Constructs a new EventException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input format is incorrect
     * for the event command. This constructor is used for general format
     * validation failures.
     */
    public EventException() {
        super(COMMAND_TYPE, DEFAULT_HELP);
    }

    public static EventException missingFromKeyword() {
        return new EventException("Missing '/from' keyword in event command");
    }

    public static EventException missingToKeyword() {
        return new EventException("Missing '/to' keyword in event command");
    }

    public static EventException dateParsingError(String details) {
        return new EventException("Date format error: " + details);
    }

    private EventException(String message) {
        super(message);
    }
}
