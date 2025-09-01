package MayoBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs during a specific time period.
 * Extends the base Task class to include event functionality with start
 * and end times, supporting both user input parsing and programmatic creation.
 * Handles date/time parsing, formatting, and storage for time-bound events.
 * <p>
 * EventTask manages two datetime fields for the event duration and provides
 * appropriate formatting for both user display and file storage. The class
 * handles date parsing errors gracefully with descriptive exception messages.
 */
public class EventTask extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Creates a new EventTask with the specified description and time period strings.
     * Parses the start and end time strings using the expected input format and
     * creates an event task with the provided description. Both time strings must
     * match the format "dd-MM-yyyy HH:mm" for successful parsing.
     * <p>
     * This constructor is typically used when creating tasks from user input
     * where the event times are provided as formatted strings. Date parsing is
     * performed immediately for both times and any format errors result in exceptions.
     *
     * @param description the descriptive text for this event task
     * @param from the start time string in format "dd-MM-yyyy HH:mm"
     * @param to the end time string in format "dd-MM-yyyy HH:mm"
     * @throws IllegalArgumentException if either date string format is invalid
     */
    public EventTask(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDateTime.parse(from.trim(), INPUT_FORMAT);
            this.to = LocalDateTime.parse(to.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Dates must be in format dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Creates a new EventTask with the specified description and time period.
     * Creates an event task using pre-parsed LocalDateTime objects for the
     * start and end times. This constructor is typically used when loading tasks
     * from storage or when the times are already available as LocalDateTime objects.
     * <p>
     * This constructor bypasses date parsing and validation, assuming the
     * LocalDateTime objects are valid and properly formatted. It's primarily
     * used by the storage system during task reconstruction.
     *
     * @param description the descriptive text for this event task
     * @param from the start time as a LocalDateTime object
     * @param to the end time as a LocalDateTime object
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the file storage representation of this event task.
     * Extends the base task file format by prefixing with "E" and appending
     * both start and end times in ISO format. The format follows the pattern:
     * "E | completion_status | description | iso_start_datetime | iso_end_datetime"
     * <p>
     * The ISO datetime format ensures consistent and unambiguous date
     * representation in storage files, facilitating reliable task reconstruction
     * when loading from persistent storage.
     *
     * @return the file format string with event task identifier and time period
     */
    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat()
                + " | " + from
                + " | " + to;
    }

    /**
     * Returns the display string representation of this event task.
     * Extends the base task display format by prefixing with "[E]" and
     * appending the formatted start and end times. The times are displayed
     * in a user-friendly format for easy reading and understanding.
     * <p>
     * The display format shows the task type, completion status, description,
     * and time period in a format optimized for console display and user readability.
     *
     * @return the display string with event task type indicator and formatted time period
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from:" + from.format(DISPLAY_FORMAT)
                + " | to:" + to.format(DISPLAY_FORMAT) + ")";
    }
}
