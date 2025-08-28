import exceptions.MayoBotException;

public class Command {
    private String command;
    private String arguments;

    public Command(String command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    public static void execute(Command commandInput, TaskList taskList) throws MayoBotException {
        boolean success;
        String command = commandInput.getCommand();
        String arguments = commandInput.getArguments();
        switch (command) {
            case "list":
                System.out.println("\tHere are the tasks in your list:");
                taskList.printTasks();
                break;
            case "mark":
                if (arguments.trim().isEmpty()) {
                    throw new exceptions.MarkException();
                }
                try {
                    int markIndex = Integer.parseInt(arguments);
                    success = taskList.markTaskAsDone(markIndex);
                    if (success) {
                        System.out.println("\tNice! I've marked this task as done:");
                        taskList.printTask(markIndex);
                    } else {
                        System.out.println("\tSorry, I was not able to mark the specified task as done.");
                    }
                } catch (NumberFormatException e) {
                    throw new exceptions.MarkException();
                }
                break;
            case "unmark":
                if (arguments.trim().isEmpty()) {
                    throw new exceptions.UnmarkException();
                }
                try {
                    int unmarkIndex = Integer.parseInt(arguments);
                    success = taskList.markTaskAsNotDone(unmarkIndex);
                    if (success) {
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        taskList.printTask(unmarkIndex);
                    } else {
                        System.out.println("\tSorry, I was not able to mark the specified task as not done yet.");
                    }
                } catch (NumberFormatException e) {
                    throw new exceptions.UnmarkException();
                }
                break;
            case "delete":
                if (arguments.trim().isEmpty()) {
                    throw new exceptions.DeleteException();
                }
                try {
                    int deleteIndex = Integer.parseInt(arguments);
                    taskList.deleteTask(deleteIndex);
                } catch (NumberFormatException e) {
                    throw new exceptions.MarkException();
                } catch (IndexOutOfBoundsException e) {
                    throw new exceptions.DeleteException();
                }
                break;
            case "todo":
                // The arguments for to-do will be the description
                if (arguments.trim().isEmpty()) {
                    throw new exceptions.TodoException();
                }
                Task newTodoTask = new TodoTask(arguments);
                taskList.addTask(newTodoTask);
                break;
            case "deadline":
                String[] deadlineParts = arguments.split(" /by", 2);
                if (deadlineParts[0].trim().isEmpty()
                        || deadlineParts.length < 2
                        || deadlineParts[1].trim().isEmpty()) {
                    throw new exceptions.MayoBotException("Input is not the correct format for " +
                            "the \"deadline\" command.");
                }
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts[1];
                Task newDeadlineTask = new DeadlineTask(deadlineDescription, by);
                taskList.addTask(newDeadlineTask);
                break;
            case "event":
                String[] fromSplit = arguments.split(" /from", 2);
                if (fromSplit[0].trim().isEmpty() || fromSplit.length < 2) {
                    throw new exceptions.EventException();
                }
                String eventDescription = fromSplit[0];
                String fromAndTo = fromSplit[1];
                String[] toSplit = fromAndTo.split("/to", 2);
                if (toSplit[0].trim().isEmpty() || toSplit.length < 2 || toSplit[1].trim().isEmpty()) {
                    throw new exceptions.EventException();
                }
                String eventFrom = toSplit[0];
                String eventTo = toSplit[1];
                Task newEventTask = new EventTask(eventDescription, eventFrom, eventTo);
                taskList.addTask(newEventTask);
                break;
            default:
                throw new exceptions.UnknownCommandException(command);
        }
    }
}
