package mayobot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import mayobot.task.Task;
import mayobot.task.TaskList;

/**
 * Handles persistent storage operations for task data.
 * Manages loading tasks from file storage at application startup and saving
 * task data to file storage during application execution. Provides both
 * individual task saving and bulk task list saving capabilities.
 * <p>
 * The storage system uses a simple text-based format where each line represents
 * one task separated by | characters. Directory creation is handled automatically
 * to ensure the storage path exists before file operations.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     * The file path will be used for all subsequent load and save operations.
     * Directory creation is handled automatically during file operations.
     *
     * @param filePath the path to the file where tasks should be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a new TaskList after loading all tasks from the storage file.
     * Creates the necessary directory structure and file if they don't exist.
     * Parses each line of the file as a task using the Parser utility and
     * adds valid tasks to the returned TaskList.
     * <p>
     * If the file doesn't exist, creates a new empty file and returns an empty
     * TaskList. Invalid task lines are silently skipped during loading.
     * The returned TaskList is configured with this Storage instance for
     * subsequent save operations.
     *
     * @return a TaskList containing all valid tasks loaded from the file
     * @throws IOException if file creation or reading operations fail
     * @see Parser#parseTaskFromFile(String)
     * @see TaskList
     */
    public TaskList loadTasks() throws IOException {
        TaskList taskList = new TaskList(this);
        File file = new File(filePath);

        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            System.out.println("\tTasks file created.\n");
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

    /**
     * Saves a single task to the storage file by appending to the existing content.
     * Creates the necessary directory structure if it doesn't exist and appends
     * the task in file format to the end of the storage file. This method is
     * typically used when adding new tasks during application execution.
     * <p>
     * File operations are performed with automatic resource management to ensure
     * proper file closure. IOException during writing is caught and printed to
     * stderr, but does not propagate to the caller.
     *
     * @param task the task to append to the storage file
     * @see Task#changeToFileFormat()
     */
    public void saveTask(Task task) {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(task.changeToFileFormat() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves all tasks from the TaskList to the storage file by overwriting existing content.
     * Creates the necessary directory structure if it doesn't exist and writes
     * all tasks in the TaskList to the storage file, replacing any existing content.
     * This method is typically used for bulk save operations or when task order changes.
     * <p>
     * File operations are performed with automatic resource management to ensure
     * proper file closure. IOException during writing is caught and printed to
     * stderr, but does not propagate to the caller.
     *
     * @param taskList the TaskList containing all tasks to save to the file
     * @see Task#changeToFileFormat()
     * @see TaskList
     */
    public void saveTasks(TaskList taskList) {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                writer.write(task.changeToFileFormat() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
