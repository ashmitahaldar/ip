package mayobot;

import javafx.application.Application;

/**
 * The main entry point for the MayoBot application.
 * This launcher class provides a unified entry point that can start the application
 * in either Command Line Interface (CLI) mode or Graphical User Interface (GUI) mode
 * based on the command line arguments provided.
 * Usage:
 * <ul>
 *   <li>CLI mode: {@code java Launcher cli} - Starts the application in terminal mode</li>
 *   <li>GUI mode: {@code java Launcher} - Starts the JavaFX graphical interface (default)</li>
 * </ul>
 *
 * The CLI mode uses a predefined data file location at {@code ./data/tasks.txt},
 * while the GUI mode delegates to the {@link Main} class for JavaFX application lifecycle management.
 */
public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("cli")) {
            new MayoBot("./data/tasks.txt").run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
