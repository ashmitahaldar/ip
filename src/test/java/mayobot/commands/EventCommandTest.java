package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.EventException;
import mayobot.exceptions.MayoBotException;

public class EventCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "event_command_test.txt";
    }

    @Test
    public void eventCommand_validEvent_addsTask() throws MayoBotException {
        int initialSize = taskList.getSize();
        EventCommand command = new EventCommand("team meeting /from 15-01-2024 14:00 /to 15-01-2024 16:00");

        String result = command.execute(ui, taskList, false);

        assertEquals(initialSize + 1, taskList.getSize());
        assertTrue(result.contains("added"));
        assertTrue(result.contains("team meeting"));
    }

    @Test
    public void eventCommand_emptyDescription_throwsEventException() {
        EventCommand command = new EventCommand("");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_missingFromKeyword_throwsEventException() {
        EventCommand command = new EventCommand("team meeting 15-01-2024 /to 15-01-2024 16:00");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_missingToKeyword_throwsEventException() {
        EventCommand command = new EventCommand("team meeting /from 15-01-2024 14:00");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_emptyFromTime_throwsEventException() {
        EventCommand command = new EventCommand("team meeting /from /to 15-01-2024 16:00");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_emptyToTime_throwsEventException() {
        EventCommand command = new EventCommand("team meeting /from 15-01-2024 14:00 /to");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_whitespaceOnlyDescription_throwsEventException() {
        EventCommand command = new EventCommand("   /from 15-01-2024 14:00 /to 15-01-2024 16:00");
        assertThrows(EventException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void eventCommand_guiMode_returnsResponse() throws MayoBotException {
        EventCommand command = new EventCommand("test event /from 15-01-2024 14:00 /to 15-01-2024 16:00");

        String result = command.execute(ui, taskList, true);

        assertTrue(result.contains("added"));
        assertTrue(result.contains("test event"));
    }
}
