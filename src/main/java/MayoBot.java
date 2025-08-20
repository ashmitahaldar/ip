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
            printLine();
            if (input.equalsIgnoreCase("bye")) {
                done = true;
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printTasks();
            } else {
                taskList.addTask(input);
                System.out.println("\tadded: " + input);
            }
            printLine();
        }

        scanner.close();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    private static class TaskList {
        private String[] tasks;
        private int taskCounter;

        public TaskList() {
            tasks = new String[100];
            taskCounter = 0;
        }

        public void addTask(String task) {
            tasks[taskCounter] = task;
            taskCounter++;
        }

        public void printTasks() {
            for (int i = 0; i < taskCounter; i++) {
                System.out.println("\t" + (i + 1) + ". " + tasks[i]);
            }
        }
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
