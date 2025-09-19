package mayobot.commands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import mayobot.Storage;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

public abstract class BaseCommandTest {
    protected TaskList taskList;
    protected Ui ui;
    protected ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    protected abstract String getTestFileName();

    @BeforeEach
    public void setUp() {
        String testFile = "./test_data/" + getTestFileName();
        Storage storage = new Storage(testFile);
        taskList = new TaskList(storage);
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(originalOut);
        ui.close();

        String testFile = "./test_data/" + getTestFileName();
        Path testFilePath = Paths.get(testFile);
        Path testDir = Paths.get("./test_data");

        if (Files.exists(testFilePath)) {
            Files.delete(testFilePath);
        }
        if (Files.exists(testDir) && Files.list(testDir).findAny().isEmpty()) {
            Files.delete(testDir);
        }
    }
}
