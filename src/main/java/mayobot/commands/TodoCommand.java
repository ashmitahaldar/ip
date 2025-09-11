package mayobot.commands;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.TodoException;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.task.TodoTask;
import mayobot.ui.Ui;

public class TodoCommand extends Command {
    public TodoCommand(String arguments) {
        super("todo", arguments);
    }

    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            throw new TodoException();
        }
        Task newTodoTask = new TodoTask(arguments);
        String addTodoTaskMessage = taskList.addTask(newTodoTask, ui, isGui);
        if (!isGui) {
            ui.showMessage(addTodoTaskMessage);
            return null;
        }
        return buildResponse(addTodoTaskMessage);
    }
}
