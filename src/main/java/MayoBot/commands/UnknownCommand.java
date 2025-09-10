package MayoBot.commands;

import MayoBot.exceptions.MayoBotException;
import MayoBot.exceptions.UnknownCommandException;
import MayoBot.task.TaskList;
import MayoBot.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String command, String arguments) {
        super(command, arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        throw new UnknownCommandException(this.getCommand());
    }
}
