package MayoBot.commands;

import MayoBot.exceptions.MayoBotException;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String arguments) {
        super("list", arguments);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        if (isGui) {
            return super.buildResponse("Here are the tasks in your list:\n" + taskList.getTasksForGui());
        } else {
            ui.showMessage("Here are the tasks in your list:");
            taskList.printTasks(ui);
        }
        return null;
    }
}
