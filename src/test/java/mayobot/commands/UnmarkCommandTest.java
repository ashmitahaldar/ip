package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.UnmarkException;
import mayobot.task.TodoTask;

public class UnmarkCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "unmark_command_test.txt";
    }

    @Test
    public void unmarkCommand_validIndex_unmarksTask() throws MayoBotException {
        TodoTask task = new TodoTask("test task");
        task.markAsDone();
        taskList.addTaskToList(task);

        UnmarkCommand command = new UnmarkCommand("1");
        String result = command.execute(ui, taskList, false);

        assertFalse(taskList.getTask(0).isDone());
        assertTrue(result.contains("unmarked") || result.contains("not done"));
        assertFalse(command.isExit());
    }

    @Test
    public void unmarkCommand_emptyArguments_throwsUnmarkException() {
        UnmarkCommand command = new UnmarkCommand("");
        assertThrows(UnmarkException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void unmarkCommand_invalidIndex_throwsUnmarkException() {
        UnmarkCommand command = new UnmarkCommand("999");
        assertThrows(UnmarkException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void unmarkCommand_nonNumericIndex_throwsUnmarkException() {
        UnmarkCommand command = new UnmarkCommand("abc");
        assertThrows(UnmarkException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void unmarkCommand_alreadyUnmarkedTask_remainsUnmarked() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));

        UnmarkCommand command = new UnmarkCommand("1");
        command.execute(ui, taskList, false);

        assertFalse(taskList.getTask(0).isDone());
    }

    @Test
    public void unmarkCommand_zeroIndex_throwsUnmarkException() {
        taskList.addTaskToList(new TodoTask("test task"));
        UnmarkCommand command = new UnmarkCommand("0");
        assertThrows(UnmarkException.class, () -> command.execute(ui, taskList, false));
    }
}
