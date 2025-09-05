package MayoBot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import MayoBot.ui.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private Ui ui;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        ui.close();
    }

    @Test
    public void ui_showWelcome_displaysWelcomeAndLogo() {
        ui.showWelcome();

        String output = outputStream.toString();
        assertTrue(output.contains("Hello, I'm MayoBot!"));
        assertTrue(output.contains("What can I do for you?"));
        assertTrue(output.contains("----")); // Part of the line separator
        // Check if logo is displayed (contains part of ASCII art)
        assertTrue(output.contains(",---."));
    }

    @Test
    public void ui_showGoodbye_displaysGoodbyeMessage() {
        ui.showGoodbye();

        String output = outputStream.toString();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
        assertTrue(output.contains("----")); // Part of the line separator
    }

    @Test
    public void ui_readCommand_readsUserInput() {
        String testInput = "test command";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        // Create new Ui instance after setting input
        Ui testUi = new Ui();
        String result = testUi.readCommand();

        assertEquals(testInput, result);
        testUi.close();
    }

    // TODO: Fix this test and feature
    //    @Test
    //    public void ui_readCommand_readsEmptyInput() {
    //        String testInput = "";
    //        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
    //
    //        Ui testUi = new Ui();
    //        String result = testUi.readCommand();
    //
    //        assertEquals(testInput, result);
    //        testUi.close();
    //    }

    @Test
    public void ui_readCommand_readsInputWithNewline() {
        String testInput = "command with newline\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));

        Ui testUi = new Ui();
        String result = testUi.readCommand();

        assertEquals("command with newline", result);
        testUi.close();
    }

    @Test
    public void ui_showMessage_displaysMessageWithTab() {
        String testMessage = "This is a test message";
        ui.showMessage(testMessage);

        String output = outputStream.toString();
        assertTrue(output.contains("\t" + testMessage));
    }

    @Test
    public void ui_showMessage_displaysEmptyMessage() {
        String testMessage = "";
        ui.showMessage(testMessage);

        String output = outputStream.toString();
        assertTrue(output.contains("\t"));
    }

    @Test
    public void ui_showError_displaysErrorWithTab() {
        String errorMessage = "This is an error message";
        ui.showError(errorMessage);

        String output = outputStream.toString();
        assertTrue(output.contains("\t" + errorMessage));
    }

    @Test
    public void ui_showLine_displaysLineSeparator() {
        ui.showLine();

        String output = outputStream.toString();
        assertTrue(output.contains(
                "--------------------------------------------------------------------------------------"));
        assertTrue(output.contains("\t"));
    }

    @Test
    public void ui_showLine_displaysMultipleLines() {
        ui.showLine();
        ui.showMessage("Test message");
        ui.showLine();

        String output = outputStream.toString();
        long lineCount = output.lines()
                .filter(line -> line.contains(
                        "--------------------------------------------------------------------------------------"))
                .count();
        assertEquals(2, lineCount);
    }

    @Test
    public void ui_close_closesScanner() {
        // This test ensures close() doesn't throw exceptions
        assertDoesNotThrow(() -> ui.close());
    }

    @Test
    public void ui_close_handlesMultipleCloseCalls() {
        assertDoesNotThrow(() -> {
            ui.close();
            ui.close(); // Should not throw exception
        });
    }

    @Test
    public void ui_showWelcome_completeSequence() {
        ui.showWelcome();

        String output = outputStream.toString();

        // Check sequence: logo, line, message, message, line
        String[] lines = output.split("\n");
        assertTrue(lines.length > 5);

        // Should contain logo (ASCII art)
        boolean hasLogo = output.contains(",---.");
        assertTrue(hasLogo);

        // Should contain welcome messages
        assertTrue(output.contains("Hello, I'm MayoBot!"));
        assertTrue(output.contains("What can I do for you?"));

        // Should have line separators
        long separatorCount = output.lines()
                .filter(line -> line.contains(
                        "--------------------------------------------------------------------------------------"))
                .count();
        assertEquals(2, separatorCount);
    }
}
