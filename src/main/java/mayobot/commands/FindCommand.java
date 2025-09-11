package mayobot.commands;

import java.util.ArrayList;

import mayobot.exceptions.MayoBotException;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String arguments) {
        super("find", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            if (!isGui) {
                ui.showMessage("Please specify a search term.");
            }
            return buildResponse("Please specify a search term.");
        } else {
            ArrayList<Object[]> matchingTasks = taskList.findTask(arguments.trim());
            if (matchingTasks.isEmpty()) {
                if (!isGui) {
                    ui.showMessage("No matching tasks found.");
                }
                return buildResponse("No matching tasks found.");
            } else {
                StringBuilder response = new StringBuilder();
                if (!isGui) {
                    ui.showMessage("Here are the matching tasks in your list:");
                }
                response.append("Here are the matching tasks in your list:\n");
                for (Object[] matchingTask : matchingTasks) {
                    int index = (Integer) matchingTask[0];
                    Task task = (Task) matchingTask[1];
                    if (!isGui) {
                        ui.showMessage(index + ". " + task);
                    }
                    response.append(index + ". " + task + "\n");
                }
                return buildResponse(response.toString());
            }
        }
    }
}
