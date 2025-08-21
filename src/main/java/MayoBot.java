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
            String[] tokens = input.split(" ");
            printLine();
            if (tokens[0].equalsIgnoreCase("bye")) {
                done = true;
                break;
            }
            boolean success = false;
            switch (tokens[0]) {
                case "list":
                    System.out.println("\tHere are the tasks in your list:");
                    taskList.printTasks();
                    break;
                case "mark":
                    success = taskList.markTaskAsDone(Integer.parseInt(tokens[1]));
                    if (success) {
                        System.out.println("\tNice! I've marked this task as done:");
                        taskList.printTask(Integer.parseInt(tokens[1]));
                    } else {
                        System.out.println("\tSorry, I was not able to mark the specified task as done.");
                    }
                    break;
                case "unmark":
                    success = taskList.markTaskAsNotDone(Integer.parseInt(tokens[1]));
                    if (success) {
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        taskList.printTask(Integer.parseInt(tokens[1]));
                    } else {
                        System.out.println("\tSorry, I was not able to mark the specified task as not done yet.");
                    }
                    break;
                default:
                    taskList.addTask(input);
                    System.out.println("\tadded: " + input);
                    break;
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
}
