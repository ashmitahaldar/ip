package MayoBot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MayoBotTest {
    private final String TEST_FILE = "./test_data/mayobot_test.txt";
    private final String TEST_DIR = "./test_data";
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() throws IOException {
        System.setOut(originalOut);

        Path testFile = Paths.get(TEST_FILE);
        Path testDir = Paths.get(TEST_DIR);

        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
        if (Files.exists(testDir)) {
            Files.delete(testDir);
        }
    }

    // MayoBot should initialize successfully with valid file path
    @Test
    public void mayoBot_initialization_successful() {
        MayoBot bot = new MayoBot(TEST_FILE);
        assertNotNull(bot);
    }

    // TODO: Test that MayoBot should handle non-existent file during initialization

    @Test
    @DisplayName("MayoBot should load existing tasks from file")
    public void mayoBot_loadTaskList_existingFile() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        String taskData = "T | 0 | existing task\nT | 1 | completed task\n";
        Files.write(Paths.get(TEST_FILE), taskData.getBytes());

        MayoBot bot = new MayoBot(TEST_FILE);

        String output = outputStream.toString();
        assertFalse(output.contains("Creating new task list"));
    }

    // MayoBot should handle IOException during task loading
    @Test
    public void mayoBot_loadTaskList_ioException() {
        String invalidPath = "/invalid/readonly/path.txt";

        MayoBot bot = new MayoBot(invalidPath);

        String output = outputStream.toString();
        assertTrue(output.contains("Creating new task list"));
    }
}
