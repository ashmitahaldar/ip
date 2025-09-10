package MayoBot.commands;

import MayoBot.exceptions.DeadlineException;
import MayoBot.exceptions.MayoBotException;
import MayoBot.task.DeadlineTask;
import MayoBot.task.Task;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String arguments) {
        super("deadline", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();

        String[] deadlineParts = arguments.split(" /by", 2);
        if (deadlineParts[0].trim().isEmpty()
                || deadlineParts.length < 2
                || deadlineParts[1].trim().isEmpty()) {
            throw new DeadlineException();
        }
        String deadlineDescription = deadlineParts[0];
        String by = deadlineParts[1];

        try {
            Task newDeadlineTask = new DeadlineTask(deadlineDescription, by);
            String addDeadlineTaskMessage = taskList.addTask(newDeadlineTask, ui, isGui);
            if (!isGui) {
                ui.showMessage(addDeadlineTaskMessage);
            }
            return buildResponse(addDeadlineTaskMessage);
        } catch (IllegalArgumentException e) {
            throw new MayoBotException("Date format error: " + e.getMessage());
        }
    }
}
