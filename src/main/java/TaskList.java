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
