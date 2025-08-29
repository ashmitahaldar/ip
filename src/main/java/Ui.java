import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLogo();
        showLine();
        System.out.println("\tHello, I'm MayoBot!");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showMessage(String message) {
        System.out.println("\t" + message);
    }

    public void showError(String message) {
        System.out.println("\t" + message);
    }

    public void showLine() {
        String line = "\t--------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    private void showLogo() {
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
