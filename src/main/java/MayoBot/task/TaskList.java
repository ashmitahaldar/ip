package MayoBot.task;

import MayoBot.Storage;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage;

    public TaskList(Storage storage) {
        tasks = new ArrayList<>();
        this.storage = storage;
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        storage.saveTask(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
    }

    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
        storage.saveTasks(this);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + deletedTask);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
    }

    public void printTask(int index) {
        System.out.println("\t" + tasks.get(index - 1));
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + task);
        }
    }

    public boolean markTaskAsDone(int index) {
        if (index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            storage.saveTasks(this);
            return true;
        } else {
            return false;
        }

    }

    public boolean markTaskAsNotDone(int index) {
        if (index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            storage.saveTasks(this);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds and displays all tasks that contain the specified search term.
     * Searches through all task descriptions for the given keyword and displays
     * matching tasks with their original numbering. The search is case-insensitive
     * and matches partial strings within task descriptions.
     * <p>
     * If no tasks match the search term, displays an appropriate message to the user.
     * Matching tasks are displayed with the same formatting as the regular task list,
     * including task type indicators and completion status.
     *
     * @param searchTerm the keyword to search for in task descriptions
     */
    public void findTask(String searchTerm) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        ArrayList<Integer> matchingIndices = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingTasks.add(task);
                matchingIndices.add(i + 1); // Store 1-based index
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("\tNo matching tasks found.");
        } else {
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println("\t" + matchingIndices.get(i) + "." + matchingTasks.get(i));
            }
        }
    }
}
