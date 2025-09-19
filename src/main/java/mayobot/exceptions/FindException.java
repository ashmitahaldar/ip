package mayobot.exceptions;

/**
 * Exception thrown when errors occur during task search operations in the MayoBot application.
 * <p>
 * This exception is specifically used by the FindCommand to handle various search-related
 * error conditions, including:
 * <ul>
 *   <li>Missing or empty search terms</li>
 *   <li>Invalid search parameters or flags</li>
 *   <li>Search operation failures</li>
 *   <li>Task list access errors during search</li>
 * </ul>
 * <p>
 * As a subclass of MayoBotException, this exception maintains consistency with the
 * application's error handling framework while providing specific context for
 * find-related operations.
 *
 * @see mayobot.commands.FindCommand
 * @see MayoBotException
 */
public class FindException extends MayoBotException {
    /**
     * Constructs a new FindException with the specified error message.
     * <p>
     * The message should provide clear information about what went wrong
     * during the search operation to help users understand and correct the issue.
     *
     * @param message the detailed error message describing the search failure
     */
    public FindException(String message) {
        super(message);
    }
}
