package MayoBot.commands;

import MayoBot.exceptions.MayoBotException;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String arguments) {
        super("bye", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        this.isExit = true;
        return null;
    }
}
