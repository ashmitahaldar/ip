package MayoBot.commands;

import MayoBot.exceptions.EventException;
import MayoBot.exceptions.MayoBotException;
import MayoBot.task.EventTask;
import MayoBot.task.Task;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class EventCommand extends Command {
    public EventCommand(String arguments) {
        super("event", arguments);
    }

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
