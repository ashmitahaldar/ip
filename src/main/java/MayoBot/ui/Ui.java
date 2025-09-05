package MayoBot.ui;

import java.util.Scanner;

/**
 * Handles all user interface interactions and display formatting.
 * Manages console input/output operations including welcome messages, user input reading,
 * message display, and application branding. Provides consistent formatting and
 * visual separation for all user-facing content.
 * <p>
 * The UI maintains a Scanner for reading user input and provides methods for
 * displaying various types of messages with consistent indentation and line separation.
 * All output includes tab indentation for visual consistency.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui instance and initializes the input scanner.
     * Sets up the Scanner to read from System.in for subsequent user input
     * operations. The scanner should be closed when the UI is no longer needed.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message sequence including logo and greeting.
     * Shows the application logo, followed by line separators and welcome text.
     * This method is typically called at application startup to greet the user
     * and establish the visual branding of the application.
     * <p>
     * The welcome sequence includes the MayoBot ASCII art logo, greeting message,
     * and prompt for user interaction, all formatted with consistent line separators.
     */
    public void showWelcome() {
        showLogo();
        showLine();
        System.out.println("\tHello, I'm MayoBot!");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public String getWelcome() {
        StringBuilder welcome = new StringBuilder();
        welcome.append("Hello, I'm MayoBot!" + "\n");
        welcome.append("What can I do for you?");
        return welcome.toString();
    }

    /**
     * Displays the goodbye message with line separator.
     * Shows a farewell message to the user when the application is terminating.
     * This method is typically called when the user issues a "bye" command
     * or when the application is shutting down normally.
     */
    public void showGoodbye() {
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }


    /**
     * Reads and returns the next line of user input from the console.
     * Blocks until the user enters a complete line and presses Enter.
     * The returned string does not include the trailing newline character.
     * <p>
     * This method is used by the main application loop to get user commands
     * for processing. Input validation and parsing are handled by other components.
     *
     * @return the complete line of user input as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Closes the input scanner and releases associated resources.
     * Should be called when the UI is no longer needed to properly clean up
     * the Scanner and its underlying input stream. After calling this method,
     * readCommand() should not be called.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Displays a message to the user with consistent tab indentation.
     * Formats and prints the message with a leading tab character for
     * visual consistency with other application output. Used for general
     * informational messages and command responses.
     *
     * @param message the message text to display to the user
     */
    public void showMessage(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Displays an error message to the user with consistent tab indentation.
     * Formats and prints the error message with a leading tab character for
     * visual consistency. Currently uses the same formatting as regular messages,
     * but provides semantic separation for error handling.
     *
     * @param message the error message text to display to the user
     */
    public void showError(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Displays a horizontal line separator for visual organization.
     * Prints a tab-indented line of dashes to separate different sections
     * of output and improve visual readability. Used throughout the application
     * to create clear boundaries between different types of content.
     */
    public void showLine() {
        String line = "\t--------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    /**
     * Displays the MayoBot ASCII art logo.
     * Prints the application's visual branding as ASCII art text. The logo
     * is displayed as part of the welcome sequence and helps establish the
     * application's identity and visual appeal.
     */
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
