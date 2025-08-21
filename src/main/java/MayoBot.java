import exceptions.MayoBotException;

import java.util.Scanner;

public class MayoBot {
    public static void main(String[] args) {
        String name = "MayoBot";
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        TaskList taskList = new TaskList();

        printLogo();
        printLine();
        System.out.println("\tHello, I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printLine();

        while (!done) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String arguments = parts.length > 1 ? parts[1] : "";
            printLine();
            if (command.equalsIgnoreCase("bye"))  {
                done = true;
                break;
            }
            try {
                runCommand(command, arguments, taskList);
            } catch (exceptions.MayoBotException e) {
                System.out.println("\t" + e.getMessage());
            }
            printLine();
        }

        scanner.close();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    private static void printLine() {
        String line = "\t--------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    private static void printLogo() {
        String logo = ",---.    ,---.   ____       ____     __   ,-----.     _______       ,-----.  ,---------.\n"
                + "|    \\  /    | .'  __ `.    \\   \\   /  /.'  .-,  '.  \\  ____  \\   .'  .-,  '.\\          \\\n"
                + "|  ,  \\/  ,  |/   '  \\  \\    \\  _. /  '/ ,-.|  \\ _ \\ | |    \\ |  / ,-.|  \\ _ \\`--.  ,---'\n"
                + "|  |\\_   /|  ||___|  /  |     _( )_ .';  \\  '_ /  | :| |____/ / ;  \\  '_ /  | :  |   \\\n"
                + "|  _( )_/ |  |   _.-`   | ___(_ o _)' |  _`,/ \\ _/  ||   _ _ '. |  _`,/ \\ _/  |  :_ _:\n"
                + "| (_ o _) |  |.'   _    ||   |(_,_)'  : (  '\\_/ \\   ;|  ( ' )  \\: (  '\\_/ \\   ;  (_I_)\n"
                + "|  (_,_)  |  ||  _( )_  ||   `-'  /    \\ `\"/  \\  ) / | (_{;}_) | \\ `\"/  \\  ) /  (_(=)_)\n"
                + "|  |      |  |\\ (_ o _) / \\      /      '. \\_/``\".'  |  (_,_)  /  '. \\_/``\".'    (_I_)\n"
                + "'--'      '--' '.(_,_).'   `-..-'         '-----'    /_______.'     '-----'      '---'\n";
        System.out.println(logo);
    }

    private static void runCommand(String command, String arguments, TaskList taskList) throws MayoBotException {
        boolean success = false;
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
                taskList.addTask(new TodoTask(arguments));
                break;
            case "deadline":
                String[] deadlineParts = arguments.split(" /by", 2);
                if (deadlineParts[0].trim().isEmpty() || deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                    throw new exceptions.MayoBotException("Input is not the correct format for the \"deadline\" command.");
                }
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts[1];
                taskList.addTask(new DeadlineTask(deadlineDescription, by));
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
                taskList.addTask(new EventTask(eventDescription, eventFrom, eventTo));
                break;
            default:
                throw new exceptions.UnknownCommandException(command);
        }
    }
}
