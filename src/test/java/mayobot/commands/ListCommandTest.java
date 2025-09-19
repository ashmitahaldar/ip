package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.MayoBotException;
import mayobot.task.TodoTask;

public class ListCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "list_command_test.txt";
    }

    @Test
    public void listCommand_emptyTaskList_showsEmptyMessage() throws MayoBotException {
        ListCommand command = new ListCommand("");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("no tasks") || result.contains("empty"));
    }

    @Test
    public void listCommand_withTasks_showsAllTasks() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("task 1"));
        taskList.addTaskToList(new TodoTask("task 2"));

        ListCommand command = new ListCommand("");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("task 1"));
        assertTrue(result.contains("task 2"));
        assertTrue(result.contains("1.") || result.contains("1)"));
        assertTrue(result.contains("2.") || result.contains("2)"));
    }

    @Test
    public void listCommand_guiMode_returnsResponse() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));

        ListCommand command = new ListCommand("");
        String result = command.execute(ui, taskList, true);

        assertTrue(result.contains("test task"));
    }

    @Test
    public void listCommand_withArguments_stillWorks() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));

        ListCommand command = new ListCommand("some arguments");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("test task"));
    }
}
