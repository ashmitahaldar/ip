import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + tasks.size() + " task(s) in the list.");
    }

    public void deleteTask(int index) {
        Task deletedTask = tasks.remove(index - 1);
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
            return true;
        } else {
            return false;
        }

    }

    public boolean markTaskAsNotDone(int index) {
        if (index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            return true;
        } else {
            return false;
        }
    }
}
