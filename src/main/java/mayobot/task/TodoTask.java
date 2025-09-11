package mayobot.task;

/**
 * Represents a simple todo task without any time constraints.
 * Extends the base Task class to provide a basic task type for general
 * to-do items that don't have specific deadlines or time periods.
 * This is the simplest task type in the MayoBot system.
 * <p>
 * TodoTask maintains only a description and completion status, making it
 * suitable for simple reminders and general task items that don't require
 * temporal scheduling or deadline tracking.
 */
public class TodoTask extends Task {

    /**
     * Creates a new TodoTask with the specified description.
     * Initializes a simple todo task with the provided description text.
     * The task starts in an incomplete state and can be marked as done
     * using inherited methods from the Task class.
     *
     * @param description the descriptive text for this todo task
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the file storage representation of this todo task.
     * Extends the base task file format by prefixing with "T" to identify
     * this as a todo task type. The format follows the pattern:
     * "T | completion_status | description"
     * <p>
     * This format is used by the storage system to save and later reconstruct
     * TodoTask instances when loading from persistent storage.
     *
     * @return the file format string with todo task identifier
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns the display string representation of this todo task.
     * Extends the base task display format by prefixing with "[T]" to
     * visually identify this as a todo task type in user-facing output.
     * <p>
     * The display format shows the task type, completion status, and
     * description in a user-friendly format suitable for console display.
     *
     * @return the display string with todo task type indicator
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
