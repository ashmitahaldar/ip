package mayobot.exceptions;

/**
 * Exception thrown when the delete command input format is incorrect.
 * <p>
 * This exception is raised when users provide invalid input for the delete command,
 * which removes tasks from the task list. The delete command expects a valid
 * task number as its argument: {@code delete <task_number>}
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>No task number provided (empty arguments)</li>
 *   <li>Task index is out of bounds (referring to a non-existent task)</li>
 *   <li>Whitespace-only input</li>
 * </ul>
 * <p>
 * This exception covers both format validation (missing arguments) and
 * range validation (invalid task indices) for the delete operation.
 */
public class DeleteException extends MayoBotException {
    private static final String COMMAND_TYPE = "delete";
    private static final String DEFAULT_HELP = "Use format: delete <task number>";

    /**
     * Constructs a new DeleteException with the default error message.
     * <p>
     * Uses a standardized message indicating that the input is incorrect
     * for the delete command. This covers both missing arguments and
     * out-of-bounds task indices.
     */
    public DeleteException() {
        super(COMMAND_TYPE, DEFAULT_HELP);
    }
}
