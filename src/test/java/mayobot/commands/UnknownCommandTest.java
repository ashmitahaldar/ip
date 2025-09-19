package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.UnknownCommandException;

public class UnknownCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "unknown_command_test.txt";
    }

    @Test
    public void unknownCommand_invalidCommand_throwsUnknownCommandException() {
        UnknownCommand command = new UnknownCommand("invalidcommand", "");

        UnknownCommandException exception = assertThrows(UnknownCommandException.class, (
                ) -> command.execute(ui, taskList, false));

        String message = exception.getMessage();
        assertTrue(message.contains("Unknown command"));
        assertTrue(message.contains("invalidcommand"));
    }

    @Test
    public void unknownCommand_emptyCommandName_throwsUnknownCommandException() {
        UnknownCommand command = new UnknownCommand("", "");

        UnknownCommandException exception = assertThrows(
                UnknownCommandException.class, () -> command.execute(ui, taskList, false));

        assertTrue(exception.getMessage().contains("Unknown command"));
    }

    @Test
    public void unknownCommand_withArguments_includesCommandInMessage() {
        UnknownCommand command = new UnknownCommand("badcommand", "arg1 arg2");

        UnknownCommandException exception = assertThrows(UnknownCommandException.class, (
            ) -> command.execute(ui, taskList, false));

        String message = exception.getMessage();
        assertTrue(message.contains("Unknown command"));
        assertTrue(message.contains("badcommand"));
    }

    @Test
    public void unknownCommand_guiMode_throwsUnknownCommandException() {
        UnknownCommand command = new UnknownCommand("test", "args");

        UnknownCommandException exception = assertThrows(UnknownCommandException.class, (
            ) -> command.execute(ui, taskList, true));

        String message = exception.getMessage();
        assertTrue(message.contains("Unknown command"));
        assertTrue(message.contains("test"));
    }

    @Test
    public void unknownCommand_specialCharacters_handlesCorrectly() {
        UnknownCommand command = new UnknownCommand("@#$%", "weird!args");

        UnknownCommandException exception = assertThrows(UnknownCommandException.class, (
            ) -> command.execute(ui, taskList, false));

        String message = exception.getMessage();
        assertTrue(message.contains("Unknown command"));
        assertTrue(message.contains("@#$%"));
    }

    @Test
    public void unknownCommand_longCommandName_handlesCorrectly() {
        String longCommand = "verylongcommandnamethatdoesnotexist";
        UnknownCommand command = new UnknownCommand(longCommand, "");

        UnknownCommandException exception = assertThrows(UnknownCommandException.class, (
            ) -> command.execute(ui, taskList, false));

        String message = exception.getMessage();
        assertTrue(message.contains("Unknown command"));
        assertTrue(message.contains(longCommand));
    }
}
