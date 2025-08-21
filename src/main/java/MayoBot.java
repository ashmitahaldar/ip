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
            runCommand(command, arguments, taskList);
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

    private static void addTask(Task task, TaskList taskList) {
        taskList.addTask(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + taskList.size() + " task(s) in the list.");
    }

    private static void runCommand(String command, String arguments, TaskList taskList) {
        boolean success = false;
        switch (command) {
            case "list":
                System.out.println("\tHere are the tasks in your list:");
                taskList.printTasks();
                break;
            case "mark":
                success = taskList.markTaskAsDone(Integer.parseInt(arguments));
                if (success) {
                    System.out.println("\tNice! I've marked this task as done:");
                    taskList.printTask(Integer.parseInt(arguments));
                } else {
                    System.out.println("\tSorry, I was not able to mark the specified task as done.");
                }
                break;
            case "unmark":
                success = taskList.markTaskAsNotDone(Integer.parseInt(arguments));
                if (success) {
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    taskList.printTask(Integer.parseInt(arguments));
                } else {
                    System.out.println("\tSorry, I was not able to mark the specified task as not done yet.");
                }
                break;
            case "todo":
                // The arguments for to-do will be the description
                addTask(new Todo(arguments), taskList);
                break;
            case "deadline":
                String[] deadlineParts = arguments.split(" /by", 2);
                String deadlineDescription = deadlineParts[0];
                String by = deadlineParts.length > 1 ? deadlineParts[1] : "";
                addTask(new Deadline(deadlineDescription, by), taskList);
                break;
            case "event":
                String[] fromSplit = arguments.split(" /from", 2);
                String eventDescription = fromSplit[0];
                String fromAndTo = fromSplit.length > 1 ? fromSplit[1] : "";
                String[] toSplit = fromAndTo.split("/to", 2);
                String eventFrom = toSplit[0];
                String eventTo = toSplit.length > 1 ? toSplit[1] : "";
                addTask(new Event(eventDescription, eventFrom, eventTo), taskList);
                break;
            default:
                System.out.println("\tUnknown command: " + command + " " + arguments);
                break;
        }
    }
}
