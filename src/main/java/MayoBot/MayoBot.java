package MayoBot;

import MayoBot.exceptions.MayoBotException;
import MayoBot.task.TaskList;

import java.io.IOException;

/**
 * Main application class that orchestrates the MayoBot task management system.
 * Coordinates interactions between the UI, storage, and task management components
 * to provide a complete command-line task management experience. Handles the
 * application lifecycle from initialization through termination.
 * <p>
 * The MayoBot maintains three core components: a UI for user interaction,
 * a Storage system for task persistence, and a TaskList for task management.
 * The application runs in a continuous loop processing user commands until
 * an exit command is received.
 */
public class MayoBot {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Creates a new MayoBot instance with the specified file path for task storage.
     * Initializes the UI, storage, and task list components. Attempts to load
     * existing tasks from the specified file path, creating a new empty task list
     * if loading fails due to file issues.
     * <p>
     * The file path should point to a location where task data can be persistently
     * stored. Directory creation is handled automatically by the storage component.
     *
     * @param filePath the file path where tasks should be stored and loaded from
     */
    public MayoBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = loadTaskList();
    }

    /**
     * Starts the main application loop and handles user interaction.
     * Displays the welcome message and enters a continuous loop that reads user
     * commands, parses them, executes them, and handles any resulting exceptions.
     * The loop continues until a command sets the exit flag, typically the "bye" command.
     * <p>
     * Error handling is performed within the loop to ensure the application
     * continues running even when individual commands fail. All exceptions are
     * caught and displayed to the user through the UI component. Resource cleanup
     * is performed when the loop terminates.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command commandInput = Parser.parse(input);
                ui.showLine();
                commandInput.execute(ui, taskList);
                isExit = commandInput.isExit();
            } catch (MayoBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
        ui.showGoodbye();
    }

    /**
     * Application entry point that creates and runs a MayoBot instance.
     * Creates a new MayoBot with the default task storage file path and
     * immediately starts the application loop. This method serves as the
     * standard entry point for running the application from the command line.
     * <p>
     * The default file path "./data/tasks.txt" is used for task persistence,
     * which will be created automatically if it doesn't exist.
     *
     * @param args command line arguments (currently unused)
     */
    public static void main(String[] args) {
        new MayoBot("./data/tasks.txt").run();
    }


    /**
     * Returns a TaskList after loading it from storage or a new empty list on failure.
     * Attempts to load existing tasks from the configured storage location.
     * If loading fails due to IO issues such as missing files or read errors,
     * displays an error message and creates a new empty TaskList instead.
     * <p>
     * This method ensures the application can always start successfully, even
     * when the storage file is missing, corrupted, or inaccessible. Error
     * recovery is handled gracefully by falling back to an empty task list.
     *
     * @return a TaskList containing loaded tasks, or an empty TaskList if loading fails
     */
    private TaskList loadTaskList() {
        try {
            return storage.loadTasks();
        } catch (IOException e) {
            System.out.println("\tRan into error when dealing with tasks.txt. Creating new task list...");
            return new TaskList(storage);
        }
    }
}
