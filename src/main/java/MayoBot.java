import exceptions.MayoBotException;

import java.io.IOException;

public class MayoBot {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public MayoBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = loadTaskList();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command commandInput = Parser.parse(input);
                ui.showLine();
                commandInput.execute(taskList);
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

    public static void main(String[] args) {
        new MayoBot("./data/tasks.txt").run();
    }

    private TaskList loadTaskList() {
        try {
            return storage.loadTasks();
        } catch (IOException e) {
            System.out.println("\tRan into error when dealing with tasks.txt. Creating new task list...");
            return new TaskList(storage);
        }
    }
}
