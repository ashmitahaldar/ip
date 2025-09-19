package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.MarkException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TodoTask;

public class MarkCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "mark_command_test.txt";
    }

    @Test
    public void markCommand_validIndex_marksTask() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));
        MarkCommand command = new MarkCommand("1");

        String result = command.execute(ui, taskList, false);

        assertTrue(taskList.getTask(0).isDone());
        assertTrue(result.contains("marked"));
        assertFalse(command.isExit());
    }

    @Test
    public void markCommand_invalidIndex_throwsMarkException() {
        MarkCommand command = new MarkCommand("999");
        assertThrows(MarkException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void markCommand_alreadyMarkedTask_remainsMarked() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test task"));
        taskList.getTask(0).markAsDone();

        MarkCommand command = new MarkCommand("1");
        command.execute(ui, taskList, false);

        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void markCommand_zeroIndex_throwsMarkException() {
        taskList.addTaskToList(new TodoTask("test task"));
        MarkCommand command = new MarkCommand("0");
        assertThrows(MarkException.class, () -> command.execute(ui, taskList, false));
    }
}
