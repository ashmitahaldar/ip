package mayobot.commands;

import mayobot.exceptions.MarkException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public class MarkCommand extends Command {
    public MarkCommand(String arguments) {
        super("mark", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        boolean success = false;
        if (arguments.trim().isEmpty()) {
            throw new MarkException();
        }
        try {
            int markIndex = Integer.parseInt(arguments);
            success = taskList.markTaskAsDone(markIndex);
            if (success) {
                if (!isGui) {
                    ui.showMessage("Nice! I've marked this task as done:");
                    taskList.printTask(markIndex, ui);
                }
                return buildResponse(
                        "Nice! I've marked this task as done:\n"
                        + taskList.getTaskForGui(markIndex));
            } else {
                if (!isGui) {
                    ui.showMessage("Sorry, I was not able to mark the specified task as done.");
                }
                return buildResponse("Sorry, I was not able to mark the specified task as done.");
            }
        } catch (NumberFormatException e) {
            throw new MarkException();
        }
    }
}
