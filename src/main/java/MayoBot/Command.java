package MayoBot;

import MayoBot.exceptions.MayoBotException;
import MayoBot.exceptions.TodoException;
import MayoBot.exceptions.DeadlineException;
import MayoBot.exceptions.EventException;
import MayoBot.exceptions.MarkException;
import MayoBot.exceptions.UnmarkException;
import MayoBot.exceptions.DeleteException;
import MayoBot.exceptions.UnknownCommandException;
import MayoBot.task.Task;
import MayoBot.task.TaskList;
import MayoBot.task.TodoTask;
import MayoBot.task.DeadlineTask;
import MayoBot.task.EventTask;

public class Command {
    private String command;
    private String arguments;
    private boolean isExit;

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
        this.isExit = false;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(Ui ui, TaskList taskList) throws MayoBotException {
        boolean success;
        String command = this.getCommand();
        String arguments = this.getArguments();
        switch (command) {
            case "list":
                ui.showMessage("Here are the tasks in your list:");
                taskList.printTasks();
                break;
            case "bye":
                this.isExit = true;
                break;
            case "mark":
                if (arguments.trim().isEmpty()) {
                    throw new MarkException();
                }
                try {
                    int markIndex = Integer.parseInt(arguments);
                    success = taskList.markTaskAsDone(markIndex);
                    if (success) {
                        ui.showMessage("Nice! I've marked this task as done:");
                        taskList.printTask(markIndex);
                    } else {
                        ui.showMessage("Sorry, I was not able to mark the specified task as done.");
                    }
                } catch (NumberFormatException e) {
                    throw new MarkException();
                }
                break;
            case "unmark":
                if (arguments.trim().isEmpty()) {
                    throw new UnmarkException();
                }
                try {
                    int unmarkIndex = Integer.parseInt(arguments);
                    success = taskList.markTaskAsNotDone(unmarkIndex);
                    if (success) {
                        ui.showMessage("OK, I've marked this task as not done yet:");
                        taskList.printTask(unmarkIndex);
                    } else {
                        ui.showMessage("Sorry, I was not able to mark the specified task as not done yet.");
                    }
                } catch (NumberFormatException e) {
                    throw new UnmarkException();
                }
                break;
            case "delete":
                if (arguments.trim().isEmpty()) {
                    throw new DeleteException();
                }
                try {
                    int deleteIndex = Integer.parseInt(arguments);
                    taskList.deleteTask(deleteIndex);
                } catch (NumberFormatException e) {
                    throw new MarkException();
                } catch (IndexOutOfBoundsException e) {
                    throw new DeleteException();
                }
                break;
            case "todo":
                if (arguments.trim().isEmpty()) {
                    throw new TodoException();
                }
                Task newTodoTask = new TodoTask(arguments);
                taskList.addTask(newTodoTask);
                break;
            case "deadline":
                String[] deadlineParts = arguments.split(" /by", 2);
                if (deadlineParts[0].trim().isEmpty()
                        || deadlineParts.length < 2
                        || deadlineParts[1].trim().isEmpty()) {
                    throw new DeadlineException();
                }
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts[1];
                try {
                    Task newDeadlineTask = new DeadlineTask(deadlineDescription, by);
                    taskList.addTask(newDeadlineTask);
                } catch (IllegalArgumentException e) {
                    throw new MayoBotException("Date format error: " + e.getMessage());
                }
                break;
            case "event":
                String[] fromSplit = arguments.split(" /from", 2);
                if (fromSplit[0].trim().isEmpty() || fromSplit.length < 2) {
                    throw new EventException();
                }
                String eventDescription = fromSplit[0];
                String fromAndTo = fromSplit[1];
                String[] toSplit = fromAndTo.split("/to", 2);
                if (toSplit[0].trim().isEmpty() || toSplit.length < 2 || toSplit[1].trim().isEmpty()) {
                    throw new EventException();
                }
                String eventFrom = toSplit[0];
                String eventTo = toSplit[1];
                try {
                    Task newEventTask = new EventTask(eventDescription, eventFrom, eventTo);
                    taskList.addTask(newEventTask);
                } catch (IllegalArgumentException e) {
                    throw new MayoBotException("Date format error: " + e.getMessage());
                }
                break;
            default:
                throw new UnknownCommandException(command);
        }
    }
}
