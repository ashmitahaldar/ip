package MayoBot;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("cli")) {
            new MayoBot("./data/tasks.txt").run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
