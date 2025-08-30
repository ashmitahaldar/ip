package MayoBot;

import MayoBot.exceptions.MayoBotException;
import MayoBot.exceptions.MarkException;
import MayoBot.task.TaskList;
import MayoBot.task.TodoTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    private final String TEST_FILE = "./test_data/command_test.txt";
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
        Command command = new Command("mark", "1");

        command.execute(ui, taskList);
        assertTrue(taskList.getTask(0).isDone());
    }

    @Test
    public void command_markCommandWithEmptyArguments_exceptionThrown() {
        Command command = new Command("mark", "");
        assertThrows(MarkException.class, () -> command.execute(ui, taskList));
    }

    @Test
    public void command_byeCommand_setsExit() throws MayoBotException {
        Command command = new Command("bye", "");
        command.execute(ui, taskList);
        assertTrue(command.isExit());
    }
}