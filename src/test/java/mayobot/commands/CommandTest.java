package mayobot.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mayobot.Storage;
import mayobot.exceptions.MarkException;
import mayobot.exceptions.MayoBotException;
import mayobot.task.TaskList;
import mayobot.task.TodoTask;
import mayobot.ui.Ui;

public class CommandTest {
    private static final String TEST_FILE = "./test_data/command_test.txt";

    private TaskList taskList;
    private Ui ui;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        Storage storage = new Storage(TEST_FILE);
        taskList = new TaskList(storage);
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(System.out);
        Path testFile = Paths.get(TEST_FILE);
        Path testDir = Paths.get("./test_data");

        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
        if (Files.exists(testDir)) {
            Files.delete(testDir);
        }
    }

    @Test
    public void command_markCommandWithValidIndex_success() throws MayoBotException {
        taskList.addTaskToList(new TodoTask("test"));
        Command command = new MarkCommand("1");

        command.execute(ui, taskList, false);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void command_markCommandWithEmptyArguments_exceptionThrown() {
        Command command = new MarkCommand("");
        assertThrows(MarkException.class, () -> command.execute(ui, taskList, false));
    }

    @Test
    public void command_byeCommand_setsExit() throws MayoBotException {
        Command command = new ByeCommand("");
        command.execute(ui, taskList, false);
        assertTrue(command.isExit());
    }
}
