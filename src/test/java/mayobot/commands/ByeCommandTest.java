package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.exceptions.MayoBotException;

public class ByeCommandTest extends BaseCommandTest {

    @Override
    protected String getTestFileName() {
        return "bye_command_test.txt";
    }

    @Test
    public void byeCommand_useCommand_setsExit() throws MayoBotException {
        ByeCommand command = new ByeCommand("");
        String result = command.execute(ui, taskList, false);

        assertTrue(command.isExit());
        assertTrue(result.contains("Baiiiヾ( ˃ᴗ˂ )◞ • *✰\nSee you later°❀.ೃ࿔*"));
    }

    @Test
    public void byeCommand_withArguments_stillWorks() throws MayoBotException {
        ByeCommand command = new ByeCommand("some arguments");
        command.execute(ui, taskList, false);

        assertTrue(command.isExit());
    }

    @Test
    public void byeCommand_guiMode_returnsResponse() throws MayoBotException {
        ByeCommand command = new ByeCommand("");
        String result = command.execute(ui, taskList, true);

        assertTrue(command.isExit());
        assertTrue(result.contains("Baiiiヾ( ˃ᴗ˂ )◞ • *✰\nSee you later°❀.ೃ࿔*"));
    }
}
