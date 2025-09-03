package MayoBot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import MayoBot.task.TaskList;
import MayoBot.task.TodoTask;

public class StorageTest {
    private static final String TEST_DIR = "./test_data";
    private static final String TEST_FILE = TEST_DIR + "/test_tasks.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE);
    }

    @AfterEach
    public void tearDown() throws IOException {
        Path testFile = Paths.get(TEST_FILE);
        Path testDir = Paths.get(TEST_DIR);

        if (Files.exists(testFile)) {
            Files.delete(testFile);
        }
        if (Files.exists(testDir)) {
            Files.delete(testDir);
        }
    }

    @Test
    public void storage_loadTasks_createsNewFile() throws IOException {
        TaskList result = storage.loadTasks();
        assertNotNull(result);
        assertEquals(0, result.size());
        assertTrue(new File(TEST_FILE).exists());
    }

    @Test
    public void storage_saveTask_appendsToFile() throws IOException {
        TodoTask task = new TodoTask("test task");
        storage.saveTask(task);

        File file = new File(TEST_FILE);
        assertTrue(file.exists());

        String content = Files.readString(Paths.get(TEST_FILE));
        assertTrue(content.contains("T | 0 | test task"));
    }
}
