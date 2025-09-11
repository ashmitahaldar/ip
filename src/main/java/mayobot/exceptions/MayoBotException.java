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
        super("☹ OOPS: " + message);
    }
}
