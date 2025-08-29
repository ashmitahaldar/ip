import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "./data/tasks.txt";

    public static TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList();
        File file = new File(FILE_PATH);

        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("\tTasks file created.");
            return taskList;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseTaskFromFile(line);
                if (task != null) {
                    taskList.addTaskToList(task);
                }
            }
        }
        return taskList;
    }

    public static void saveTask(Task task) {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(task.toFileFormat() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTasks(TaskList taskList) {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                writer.write(task.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
