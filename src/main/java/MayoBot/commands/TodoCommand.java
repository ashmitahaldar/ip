package MayoBot.commands;

import MayoBot.exceptions.MayoBotException;
import MayoBot.exceptions.TodoException;
import MayoBot.task.Task;
import MayoBot.task.TaskList;
import MayoBot.task.TodoTask;
import MayoBot.ui.Ui;

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
