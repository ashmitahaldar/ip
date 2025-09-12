package mayobot.exceptions;

/**
 * Exception thrown when the user enters an unrecognized command.
 * <p>
 * This exception is raised when the parser encounters input that doesn't match
 * any of the known command types supported by MayoBot. It serves as a user-friendly
 * way to handle invalid commands instead of allowing the application to crash
 * or behave unexpectedly.
 * <p>
 * This exception includes the actual command that was entered, helping users
 * understand what went wrong and potentially correct their input.
 */
public class UnknownCommandException extends MayoBotException {
    private static final String ERROR_TEMPLATE = "Unknown command: '%s'";

    /**
     * Constructs a new UnknownCommandException with the unrecognized command.
     * <p>
     * Creates an error message that includes the specific command that was not
     * recognized, providing clear feedback to the user about what input caused
     * the error. This helps users identify typos or learn the correct command syntax.
     *
     * @param message the unrecognized command string that was entered by the user
     */
    public UnknownCommandException(String message) {
        super(String.format(ERROR_TEMPLATE, message));
    }
}
