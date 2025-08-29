package MayoBot;

import MayoBot.task.DeadlineTask;
import MayoBot.task.EventTask;
import MayoBot.task.Task;
import MayoBot.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static Command parse(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        return new Command(command, arguments);
    }

    public static Task parseTaskFromFile(String input) {
        String[] parts = input.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (type) {
        case "T":
            task = new TodoTask(description);
            break;
        case "D":
            if (parts.length >= 4) {
                LocalDateTime by = LocalDateTime.parse(parts[3]);
                task = new DeadlineTask(description, by);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                LocalDateTime from = LocalDateTime.parse(parts[3]);
                LocalDateTime to = LocalDateTime.parse(parts[4]);
                task = new EventTask(description, from, to);
            }
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}
