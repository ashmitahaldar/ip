package mayobot.exceptions;

/**
 * The base exception class for all MayoBot application-specific errors.
 * <p>
 * This exception serves as the parent class for all custom exceptions in the
 * MayoBot application. It automatically prefixes all error messages with a
 * standardized "☹ OOPS: " format to provide consistent user-facing error
 * reporting across the application.
 * <p>
 * All specific exception types (such as {@link TodoException}, {@link DeadlineException},
 * {@link EventException}, etc.) extend this base class to inherit the consistent
 * error message formatting while providing specific error handling for different
 * types of user input or application errors.
 */
public class MayoBotException extends Exception {
    private static final String ERROR_PREFIX = "☹ OOPS: ";

    /**
     * Constructs a new MayoBotException with the specified error message.
     * <p>
     * The provided message is automatically prefixed with "☹ OOPS: " to maintain
     * consistent error formatting throughout the application. This ensures all
     * MayoBot exceptions present a uniform appearance to the user.
     *
     * @param message the detailed error message describing what went wrong
     */
    public MayoBotException(String message) {
        super(ERROR_PREFIX + message);
    }

    /**
     * Constructs a new MayoBotException with a formatted error message for command-specific errors.
     * <p>
     * This constructor is specifically designed for command parsing and validation errors.
     * It creates a standardized error message that indicates the input format is incorrect
     * for a specific command type, followed by additional guidance provided in the default message.
     * <p>
     * This constructor is particularly useful for command classes that need to provide
     * specific feedback about formatting requirements while maintaining consistent error
     * message structure across different command types.
     *
     * @param commandType the name of the command that had incorrect input format (e.g., "todo", "deadline", "event")
     * @param defaultMessage additional guidance or specific formatting requirements for the command
     */
    public MayoBotException(String commandType, String defaultMessage) {
        super(ERROR_PREFIX + String.format("Input is not the correct format for the \"%s\" command. %s",
                commandType, defaultMessage));
    }
}
