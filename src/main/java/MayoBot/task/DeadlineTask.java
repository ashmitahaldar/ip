package MayoBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline for completion.
 * Extends the base Task class to include deadline functionality with
 * date and time parsing, formatting, and storage capabilities. Handles
 * both user input parsing and programmatic creation with LocalDateTime objects.
 * <p>
 * DeadlineTask supports date input from users while maintaining
 * consistent internal representation and display formatting. The class
 * handles date parsing errors gracefully by throwing descriptive exceptions.
 */
public class DeadlineTask extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected LocalDateTime by;

    /**
     * Creates a new DeadlineTask with the specified description and deadline string.
     * Parses the deadline string using the expected input format and creates
     * a deadline task with the provided description. The deadline string must
     * match the format "dd-MM-yyyy HH:mm" for successful parsing.
     * <p>
     * This constructor is typically used when creating tasks from user input
     * where the deadline is provided as a formatted string. Date parsing is
     * performed immediately and any format errors result in exceptions.
     *
     * @param description the descriptive text for this deadline task
     * @param by the deadline string in format "dd-MM-yyyy HH:mm"
     * @throws IllegalArgumentException if the date string format is invalid
     */
    public DeadlineTask(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date and time must be in format dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Creates a new DeadlineTask with the specified description and deadline.
     * Creates a deadline task using a pre-parsed LocalDateTime object for
     * the deadline. This constructor is typically used when loading tasks
     * from storage or when the deadline is already available as a LocalDateTime.
     * <p>
     * This constructor bypasses date parsing and validation, assuming the
     * LocalDateTime object is valid and properly formatted. It's primarily
     * used by the storage system during task reconstruction.
     *
     * @param description the descriptive text for this deadline task
     * @param by the deadline as a LocalDateTime object
     */
    public DeadlineTask(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the file storage representation of this deadline task.
     * Extends the base task file format by prefixing with "D" and appending
     * the deadline in ISO format. The format follows the pattern:
     * "D | completion_status | description | iso_datetime"
     * <p>
     * The ISO datetime format ensures consistent and unambiguous date
     * representation in storage files, facilitating reliable task reconstruction
     * when loading from persistent storage.
     *
     * @return the file format string with deadline task identifier and deadline
     */
    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    /**
     * Returns the display string representation of this deadline task.
     * Extends the base task display format by prefixing with "[D]" and
     * appending the formatted deadline. The deadline is displayed in a
     * user-friendly format for easy reading and understanding.
     * <p>
     * The display format shows the task type, completion status, description,
     * and deadline in a format optimized for console display and user readability.
     *
     * @return the display string with deadline task type indicator and formatted deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by:" + by.format(DISPLAY_FORMAT) + ")";
    }
}

