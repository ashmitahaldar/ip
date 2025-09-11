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

        String[] fromSplit = arguments.split(" /from", 2);
        if (fromSplit[0].trim().isEmpty() || fromSplit.length < 2) {
            throw new EventException();
        }
        String eventDescription = fromSplit[0];
        String fromAndTo = fromSplit[1];
        String[] toSplit = fromAndTo.split("/to", 2);
        if (toSplit[0].trim().isEmpty() || toSplit.length < 2 || toSplit[1].trim().isEmpty()) {
            throw new EventException();
        }
        String eventFrom = toSplit[0];
        String eventTo = toSplit[1];

        try {
            Task newEventTask = new EventTask(eventDescription, eventFrom, eventTo);
            String addEventTaskMessage = taskList.addTask(newEventTask, ui, isGui);
            if (!isGui) {
                ui.showMessage(addEventTaskMessage);
            }
            return buildResponse(addEventTaskMessage);
        } catch (IllegalArgumentException e) {
            throw new MayoBotException("Date format error: " + e.getMessage());
        }
    }
}
