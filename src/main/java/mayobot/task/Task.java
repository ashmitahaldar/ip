package mayobot.task;

/**
 * Abstract base class representing a task in the MayoBot system.
 * Provides common functionality for all task types including description management,
 * completion status tracking, and file format serialization. Concrete task types
 * extend this class to provide specific behavior and formatting.
 * <p>
 * Tasks maintain a description and completion status, and provide methods for
 * status manipulation and display formatting. The class defines the contract
 * for file serialization that all task types must implement.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * Initializes the task in an incomplete state with the provided description.
     * The task can later be marked as done or not done using the appropriate methods.
     *
     * @param description the descriptive text for this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the visual status indicator for this task's completion state.
     * Provides a single character representation that can be used in display
     * formatting to quickly show whether a task is complete or incomplete.
     * <p>
     * The status icon follows a simple convention: "X" for completed tasks
     * and a space character for incomplete tasks.
     *
     * @return "X" if the task is done, " " (space) if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the descriptive text of this task.
     * Provides access to the task's description as specified during creation.
     * The description is immutable after task creation.
     *
     * @return the task description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been marked as completed.
     * Indicates the current completion status of the task, which can be
     * modified using the markAsDone and markAsNotDone methods.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as completed.
     * Changes the task's completion status to done, which affects the
     * status icon and file format representation. The change is persistent
     * until explicitly unmarked or the application restarts.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     * Changes the task's completion status to incomplete, which affects the
     * status icon and file format representation. The change is persistent
     * until explicitly marked as done again or the application restarts.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the file storage representation of this task.
     * Provides a string format suitable for saving to persistent storage.
     * The base format includes completion status and description, with
     * concrete task types extending this format with additional information.
     * <p>
     * The format uses pipe-delimited fields: completion status (1 for done,
     * 0 for not done) followed by the task description. Subclasses may
     * append additional fields as needed.
     *
     * @return the file format string representation of this task
     */
    public String changeToFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the display string representation of this task.
     * Provides a human-readable format suitable for console display.
     * The format includes the status icon in brackets followed by the
     * task description, with concrete task types potentially adding
     * additional formatting.
     *
     * @return the display string representation of this task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
