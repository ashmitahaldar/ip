package mayobot.commands;

import mayobot.exceptions.EventException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.EventTask;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to create and add a new event task to the task list.
 * <p>
 * This command handles the creation of tasks that have both start and end times,
 * representing events or activities with a specific duration. Both "/from" and
 * "/to" keywords are required to specify the time range.
 * <p>
 * Usage: {@code event <description> /from <start_time> /to <end_time>}
 * <p>
 * Example: {@code event Team meeting /from 2024-12-25 14:00 /to 2024-12-25 16:00}
 */
public class EventCommand extends Command {
    private static final String FROM_SEPARATOR = " /from";
    private static final String TO_SEPARATOR = "/to";
    private static final int EXPECTED_PARTS = 2;

    /**
     * Constructs a new EventCommand with the specified arguments.
     *
     * @param arguments the event task description and time specification
     */
    public EventCommand(String arguments) {
        super("event", arguments);
    }

    /**
     * Executes the event command to create and add a new event task.
     * <p>
     * Parses the arguments to extract the event description, start time, and end time.
     * Validates the input format ensuring all required components are present,
     * creates a new EventTask, and adds it to the task list. Handles date format
     * errors gracefully.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list to add the new event task to
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message for GUI mode, null for CLI mode
     * @throws EventException if the input format is invalid (missing description,
     *                       missing "/from" or "/to" keywords, or empty time fields)
     * @throws MayoBotException if a date format error occurs during task creation
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        EventTaskComponents components = parseEventArguments(arguments);

        try {
            Task newEventTask = new EventTask(components.description, components.from, components.to);
            return handleTaskCreation(newEventTask, taskList, ui, isGui);
        } catch (IllegalArgumentException e) {
            // TODO: Use EventException instead.
            throw new MayoBotException(DATE_FORMAT_ERROR_PREFIX + e.getMessage());
        }
    }

    private EventTaskComponents parseEventArguments(String arguments) throws EventException {
        String[] fromSplit = arguments.split(FROM_SEPARATOR, EXPECTED_PARTS);
        validateFromSplit(fromSplit);

        String eventDescription = fromSplit[0];
        String fromAndTo = fromSplit[1];

        String[] toSplit = fromAndTo.split(TO_SEPARATOR, EXPECTED_PARTS);
        validateFromSplit(toSplit);

        String eventFrom = toSplit[0];
        String eventTo = toSplit[1];

        return new EventTaskComponents(eventDescription, eventFrom, eventTo);
    }

    private void validateFromSplit(String[] fromSplit) throws EventException {
        if (fromSplit[0].trim().isEmpty() || fromSplit.length < EXPECTED_PARTS) {
            throw new EventException();
        }
    }

    private void validateToSplit(String[] toSplit) throws EventException {
        if (toSplit[0].trim().isEmpty() || toSplit.length < EXPECTED_PARTS || toSplit[1].trim().isEmpty()) {
            throw new EventException();
        }
    }

    // Record for better data encapsulation
    private record EventTaskComponents(String description, String from, String to) {}
}
