package mayobot.exceptions;

/**
 * Exception thrown when errors occur during file storage operations.
 * <p>
 * This exception is raised when the application encounters problems while
 * reading from or writing to the task storage file ({@code tasks.txt}).
 * Storage-related errors can include file access permissions, disk space
 * issues, corrupted file format, or general I/O failures.
 * <p>
 * Common scenarios that trigger this exception:
 * <ul>
 *   <li>Unable to read from the tasks.txt file</li>
 *   <li>Unable to write to the tasks.txt file</li>
 *   <li>File permission denied errors</li>
 *   <li>Disk space full when saving tasks</li>
 *   <li>File corruption or invalid format detected</li>
 *   <li>Network drive or external storage unavailable</li>
 * </ul>
 * <p>
 * This exception helps maintain data integrity by alerting users when
 * task persistence operations fail, allowing them to take corrective
 * action before losing their task data.
 */
public class StorageException extends MayoBotException {
    /**
     * Constructs a new StorageException with the default error message.
     * <p>
     * Uses a standardized message indicating that an error occurred while
     * dealing with the tasks.txt file. This constructor is used for general
     * storage-related failures where specific details may not be available.
     */
    public StorageException() {
        super("Ran into error when dealing with tasks.txt");
    }
}
