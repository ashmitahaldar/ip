import java.util.Scanner;

public class MayoBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "MayoBot";
        boolean done = false;

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
            }
            System.out.println("\t" + input);
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
