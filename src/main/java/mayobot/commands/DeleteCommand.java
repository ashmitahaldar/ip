package mayobot.commands;

import mayobot.exceptions.DeleteException;
import mayobot.exceptions.MarkException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String arguments) {
        super("delete", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            throw new DeleteException();
        }
        try {
            int deleteIndex = Integer.parseInt(arguments);
            String deleteTaskMessage = taskList.deleteTask(deleteIndex, ui, isGui);
            if (!isGui) {
                ui.showMessage(deleteTaskMessage);
            }
            return buildResponse(deleteTaskMessage);
        } catch (NumberFormatException e) {
            throw new MarkException();
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteException();
        }
    }
}
