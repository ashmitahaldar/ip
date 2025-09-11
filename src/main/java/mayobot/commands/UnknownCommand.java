package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.UnknownCommandException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand(String command, String arguments) {
        super(command, arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        throw new UnknownCommandException(this.getCommand());
    }
}
