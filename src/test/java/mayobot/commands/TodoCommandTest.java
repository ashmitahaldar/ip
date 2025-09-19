package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.MayoBotException;
import mayobot.exceptions.TodoException;

public class TodoCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "todo_command_test.txt";
    }

    @Test
    public void todoCommand_validDescription_addsTask() throws MayoBotException {
        int initialSize = taskList.getSize();
        TodoCommand command = new TodoCommand("buy milk");

        String result = command.execute(ui, taskList, false);

        assertEquals(initialSize + 1, taskList.getSize());
        assertTrue(result.contains("added"));
        assertTrue(result.contains("buy milk"));
    }

    @Test
    public void todoCommand_emptyDescription_throwsTodoException() {
        TodoCommand command = new TodoCommand("");
        assertThrows(TodoException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void todoCommand_whitespaceOnlyDescription_throwsTodoException() {
        TodoCommand command = new TodoCommand("   ");
        assertThrows(TodoException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void todoCommand_guiMode_returnsResponse() throws MayoBotException {
        TodoCommand command = new TodoCommand("test task");

        String result = command.execute(ui, taskList, true);

        assertTrue(result.contains("added"));
        assertTrue(result.contains("test task"));
    }

    @Test
    public void todoCommand_specialCharacters_handlesCorrectly() throws MayoBotException {
        String description = "buy milk & bread @store #urgent";
        TodoCommand command = new TodoCommand(description);

        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("added"));
        assertEquals(description, taskList.getTask(taskList.getSize() - 1).getDescription());
    }
}
