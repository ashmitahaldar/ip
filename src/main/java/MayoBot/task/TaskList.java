package MayoBot.task;

import MayoBot.Storage;

import java.util.ArrayList;

/**
 * Manages a collection of tasks and provides operations for task manipulation.
 * Maintains an ordered list of tasks and coordinates with the storage system
 * to ensure task persistence. Provides methods for adding, removing, marking,
 * and displaying tasks with appropriate user feedback.
 * <p>
 * The TaskList automatically handles storage operations for task modifications
 * and provides console output for user feedback. All task operations include
 * appropriate status messages to keep the user informed of changes.
 */
public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Creates a new TaskList with the specified storage system.
     * Initializes an empty task collection and associates it with the provided
     * storage system for persistence operations. The storage system will be
     * used automatically for all task modification operations.
     *
     * @param storage the Storage instance to use for task persistence
     */
    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Returns the number of tasks currently in the list.
     * Provides a count of all tasks regardless of their completion status.
     * This count is used for user feedback and validation operations.
     *
     * @return the total number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list and saves it to storage.
     * Appends the task to the end of the task list and immediately saves
     * it to persistent storage. Displays confirmation messages to the user
     * including the added task and updated task count.
     * <p>
     * This method is used for adding new tasks during interactive use and
     * provides immediate feedback and persistence. The task is added to
     * storage incrementally without affecting existing tasks.
     *
     * @param task the task to add to the list and save to storage
     */
    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTask(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Adds a task to the list without saving to storage or displaying messages.
     * Appends the task to the end of the task list without any storage operations
     * or user feedback. This method is typically used during task loading from
     * storage when tasks are being restored from file.
     * <p>
     * Unlike addTask, this method performs no persistence operations and provides
     * no user feedback, making it suitable for bulk loading operations.
     *
     * @param task the task to add to the list only
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index position.
     * Provides direct access to tasks by their position in the list.
     * The index is zero-based, following standard Java collection conventions.
     * <p>
     * No bounds checking is performed by this method, so callers must ensure
     * the index is valid to avoid IndexOutOfBoundsException.
     *
     * @param index the zero-based index of the task to retrieve
     * @return the task at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified position and updates storage.
     * Deletes the task from the list using one-based indexing and saves
     * the updated task list to storage. Displays confirmation messages
     * showing the removed task and updated task count.
     * <p>
     * The method uses one-based indexing to match user expectations, where
     * task 1 corresponds to index 0 internally. After removal, the entire
     * task list is saved to maintain consistency in storage.
     *
     * @param index the one-based index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
        storage.saveTasks(this);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + deletedTask);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays a single task at the specified position.
     * Prints the task at the given one-based index to the console with
     * appropriate indentation. Used for showing individual tasks in response
     * to specific user queries or commands.
     * <p>
     * The method uses one-based indexing for user-friendly interaction,
     * converting internally to zero-based indexing for list access.
     *
     * @param index the one-based index of the task to display
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void printTask(int index) {
        System.out.println("\t" + tasks.get(index - 1));
    }

    /**
     * Displays all tasks in the list with numbered formatting.
     * Prints each task with its position number and content to the console.
     * Tasks are numbered starting from 1 for user-friendly display, with
     * appropriate indentation for visual consistency.
     * <p>
     * If the list is empty, no output is produced. The display format
     * includes the task number, followed by the task's string representation.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task);
        }
    }

    /**
     * Marks the specified task as completed and updates storage.
     * Changes the completion status of the task at the given one-based index
     * to done and saves the entire task list to storage. Performs bounds
     * checking to ensure the index is valid.
     * <p>
     * Returns a boolean indicating success or failure, allowing callers to
     * handle invalid indices appropriately. On success, the change is
     * immediately persisted to storage.
     *
     * @param index the one-based index of the task to mark as done
     * @return true if the task was successfully marked, false if index is invalid
     */
    public boolean markTaskAsDone(int index) {
        if (index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            storage.saveTasks(this);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Marks the specified task as not completed and updates storage.
     * Changes the completion status of the task at the given one-based index
     * to not done and saves the entire task list to storage. Performs bounds
     * checking to ensure the index is valid.
     * <p>
     * Returns a boolean indicating success or failure, allowing callers to
     * handle invalid indices appropriately. On success, the change is
     * immediately persisted to storage.
     *
     * @param index the one-based index of the task to mark as not done
     * @return true if the task was successfully unmarked, false if index is invalid
     */
    public boolean markTaskAsNotDone(int index) {
        if (index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            storage.saveTasks(this);
            return true;
        } else {
            return false;
        }
    }
}
