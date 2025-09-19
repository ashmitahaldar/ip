package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.DeleteException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TodoTask;

public class DeleteCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "delete_command_test.txt";
    }

    @Test
    public void deleteCommand_validIndex_deletesTask() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("task 1"));
        taskList.addTaskToList(new TodoTask("task 2"));
        int initialSize = taskList.getSize();

        DeleteCommand command = new DeleteCommand("1");
        String result = command.execute(ui, taskList, false);

        assertEquals(initialSize - 1, taskList.getSize());
        assertTrue(result.contains("deleted") || result.contains("removed"));
        assertFalse(command.isExit());
    }

    @Test
    public void deleteCommand_emptyArguments_throwsDeleteException() {
        DeleteCommand command = new DeleteCommand("");
        assertThrows(DeleteException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deleteCommand_invalidIndex_throwsDeleteException() {
        DeleteCommand command = new DeleteCommand("999");
        assertThrows(DeleteException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deleteCommand_nonNumericIndex_throwsDeleteException() {
        DeleteCommand command = new DeleteCommand("abc");
        assertThrows(DeleteException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deleteCommand_zeroIndex_throwsDeleteException() {
        taskList.addTaskToList(new TodoTask("test task"));
        DeleteCommand command = new DeleteCommand("0");
        assertThrows(DeleteException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deleteCommand_lastTask_deletesSuccessfully() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("only task"));

        DeleteCommand command = new DeleteCommand("1");
        String result = command.execute(ui, taskList, false);

        assertEquals(0, taskList.getSize());
        assertTrue(result.contains("deleted") || result.contains("removed"));
    }
}
