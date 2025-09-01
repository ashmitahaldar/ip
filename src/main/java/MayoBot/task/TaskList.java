package MayoBot.task;

import MayoBot.Storage;
import MayoBot.Ui;

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

    public void printTask(int index, Ui ui) {
        ui.showMessage(tasks.get(index - 1).toString());
    }

    public void printTasks(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            ui.showMessage((i + 1) + ". " + task);
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
     * Finds and returns all tasks that contain the specified search term.
     * Searches through all task descriptions for the given keyword and returns
     * matching tasks with their original numbering. The search is case-insensitive
     * and matches partial strings within task descriptions.
     * <p>
     * Returns an empty list if no tasks match the search term. The returned
     * indices correspond to the original task positions in the main task list.
     *
     * @param searchTerm the keyword to search for in task descriptions
     * @return an ArrayList containing arrays of [originalIndex, matchingTask]
     */
    public ArrayList<Object[]> findTask(String searchTerm) {
        ArrayList<Object[]> matchingResults = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingResults.add(new Object[]{i + 1, task}); // Store 1-based index and task
            }
        }

        return matchingResults;
    }
}
