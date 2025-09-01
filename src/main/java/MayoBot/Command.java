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

/**
 * Represents a user command with its arguments and provides execution logic.
 * Encapsulates the command parsing result and handles the execution of various
 * task management operations including list, mark, unmark, delete, and task creation.
 * <p>
 * Commands are created through parsing user input and contain both the command
 * word and any associated arguments. The execute method dispatches to appropriate
 * handlers based on the command type and manages the application's exit state.
 */
public class Command {
    private String command;
    private String arguments;
    private boolean isExit;

    /**
     * Creates a new Command with the specified command word and arguments.
     * Initializes the command in a non-exit state by default. The exit state
     * can be modified during execution based on the command type.
     *
     * @param command the command word identifying the operation to perform
     * @param arguments the arguments string containing parameters for the command
     */
    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
        this.isExit = false;
    }

    /**
     * Returns the command word that identifies the operation to be performed.
     * The command word is used by the execute method to determine which
     * operation handler to invoke.
     *
     * @return the command word string
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the arguments string containing parameters for the command.
     * Arguments may be empty for commands that don't require parameters,
     * or contain complex parameter strings for commands like deadline and event.
     *
     * @return the arguments string, may be empty but never null
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Returns whether this command should cause the application to exit.
     * Initially false for all commands, but becomes true when a "bye" command
     * is executed. This flag is used by the main application loop to determine
     * when to terminate.
     *
     * @return true if the application should exit after this command, false otherwise
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command using the provided UI and TaskList components.
     * Dispatches to appropriate handlers based on the command word and manages
     * user feedback through the UI. Handles argument validation and error conditions
     * by throwing specific MayoBotException subtypes.
     * <p>
     * Supported commands include:
     * - list: displays all tasks
     * - bye: sets exit flag
     * - mark/unmark: changes task completion status
     * - delete: removes tasks
     * - todo/deadline/event: creates new tasks
     * <p>
     * This method modifies the TaskList state for task management commands and
     * uses the UI for all user communication. Invalid commands or malformed
     * arguments result in appropriate exceptions being thrown.
     *
     * @param ui the UI component for user interaction and message display
     * @param taskList the TaskList to operate on for task management commands
     * @throws MayoBotException if the command is invalid or arguments are malformed
     * @see Ui
     * @see TaskList
     */
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
