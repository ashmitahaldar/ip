public class Parser {
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
                task = new DeadlineTask(description, parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                task = new EventTask(description, parts[3], parts[4]);
            }
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }
}
