package MayoBot.commands;

import MayoBot.exceptions.MayoBotException;
import MayoBot.exceptions.UnmarkException;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String arguments) {
        super("unmark", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        boolean success = false;
        if (arguments.trim().isEmpty()) {
            throw new UnmarkException();
        }
        try {
            int unmarkIndex = Integer.parseInt(arguments);
            success = taskList.markTaskAsNotDone(unmarkIndex);
            if (success) {
                if (!isGui) {
                    ui.showMessage("OK, I've marked this task as not done yet:");
                    taskList.printTask(unmarkIndex, ui);
                }
                return buildResponse(
                        "OK, I've marked this task as not done yet:\n"
                        + taskList.getTaskForGui(unmarkIndex));
            } else {
                if (!isGui) {
                    ui.showMessage("Sorry, I was not able to mark the specified task as not done yet.");
                }
                return buildResponse("Sorry, I was not able to mark the specified task as not done yet.");
            }
        } catch (NumberFormatException e) {
            throw new UnmarkException();
        }
    }
}
