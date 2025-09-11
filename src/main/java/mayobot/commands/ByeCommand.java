package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String arguments) {
        super("bye", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        this.isExit = true;
        return null;
    }
}
