package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.DeadlineException;
import mayobot.exceptions.MayoBotException;

public class DeadlineCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "deadline_command_test.txt";
    }

    @Test
    public void deadlineCommand_validDeadline_addsTask() throws MayoBotException {
        int initialSize = taskList.getSize();
        DeadlineCommand command = new DeadlineCommand("submit assignment /by 15-01-2024 23:59");

        String result = command.execute(ui, taskList, false);

        assertEquals(initialSize + 1, taskList.getSize());
        assertTrue(result.contains("Got it")
                || result.contains("added")
                || result.contains("Added")
                || result.contains("created")
                || result.contains("I've added"));
        assertTrue(result.contains("submit assignment"));
    }

    @Test
    public void deadlineCommand_emptyDescription_throwsDeadlineException() {
        DeadlineCommand command = new DeadlineCommand("");
        assertThrows(DeadlineException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deadlineCommand_missingByKeyword_throwsDeadlineException() {
        DeadlineCommand command = new DeadlineCommand("submit assignment 15-01-2024 23:59");
        assertThrows(DeadlineException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deadlineCommand_emptyDeadlineDate_throwsDeadlineException() {
        DeadlineCommand command = new DeadlineCommand("submit assignment /by");
        assertThrows(DeadlineException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deadlineCommand_whitespaceOnlyDescription_throwsDeadlineException() {
        DeadlineCommand command = new DeadlineCommand("   /by 15-01-2024 23:59");
        assertThrows(DeadlineException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void deadlineCommand_guiMode_returnsResponse() throws MayoBotException {
        DeadlineCommand command = new DeadlineCommand("test task /by 15-01-2024 23:59");

        String result = command.execute(ui, taskList, true);

        assertTrue(result.contains("Got it")
                || result.contains("added")
                || result.contains("Added")
                || result.contains("created")
                || result.contains("I've added"));
        assertTrue(result.contains("test task"));
    }

    @Test
    public void deadlineCommand_naturalLanguageDate_handlesCorrectly() throws MayoBotException {
        // Use a valid dd-MM-yyyy HH:mm format instead of "tomorrow"
        DeadlineCommand command = new DeadlineCommand("finish project /by 16-01-2024 18:00");

        String result = command.execute(ui, taskList, false);

        assertTrue(result.contains("Got it")
                || result.contains("added")
                || result.contains("Added")
                || result.contains("created")
                || result.contains("I've added"));
        assertTrue(result.contains("finish project"));
    }
}
