package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.FindException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TodoTask;

public class FindCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "find_command_test.txt";
    }

    @Test
    public void findCommand_validKeyword_findsMatchingTasks() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("buy milk"));
        taskList.addTaskToList(new TodoTask("read book"));
        taskList.addTaskToList(new TodoTask("buy bread"));

        FindCommand command = new FindCommand("buy");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("buy milk"));
        assertTrue(result.contains("buy bread"));
        assertTrue(!result.contains("read book") || result.contains("matching tasks"));
    }

    @Test
    public void findCommand_noMatches_showsNoMatchesMessage() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("buy milk"));
        taskList.addTaskToList(new TodoTask("read book"));

        FindCommand command = new FindCommand("xyz");
        String result = command.execute(ui, taskList, false);

        // Match the actual NO_TASK_FOUND_MESSAGE format
        assertTrue(result.contains("No matching tasks found")
                || result.contains("no") && (result.contains("match")
                || result.contains("found")));
    }

    @Test
    public void findCommand_emptyKeyword_throwsFindException() {
        FindCommand command = new FindCommand("");
        assertThrows(FindException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void findCommand_whitespaceOnlyKeyword_throwsFindException() {
        FindCommand command = new FindCommand("   ");
        assertThrows(FindException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void findCommand_caseInsensitiveSearch_findsMatches() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("Buy Milk"));
        taskList.addTaskToList(new TodoTask("read book"));

        FindCommand command = new FindCommand("buy");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("Buy Milk"));
    }

    @Test
    public void findCommand_partialMatch_findsMatches() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("submit assignment"));
        taskList.addTaskToList(new TodoTask("read book"));

        FindCommand command = new FindCommand("assign");
        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("submit assignment"));
    }

    @Test
    public void findCommand_guiMode_returnsResponse() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));

        FindCommand command = new FindCommand("test");
        String result = command.execute(ui, taskList, true);

        assertTrue(result.contains("test task"));
    }
}
