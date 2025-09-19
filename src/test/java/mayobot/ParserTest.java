package mayobot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.commands.ByeCommand;
import mayobot.commands.Command;
import mayobot.commands.DeadlineCommand;
import mayobot.commands.DeleteCommand;
import mayobot.commands.EventCommand;
import mayobot.commands.FindCommand;
import mayobot.commands.ListCommand;
import mayobot.commands.MarkCommand;
import mayobot.commands.TodoCommand;
import mayobot.commands.UnknownCommand;
import mayobot.commands.UnmarkCommand;
import mayobot.task.DeadlineTask;
import mayobot.task.EventTask;
import mayobot.task.Task;
import mayobot.task.TodoTask;

public class ParserTest {

    @Test
    public void parser_parseValidCommand_returnsValidCommand() {
        Command result = Parser.parse("todo buy milk");
        assertEquals("todo", result.getCommand());
        assertEquals("buy milk", result.getArguments());
    }

    @Test
    public void parser_parseCommandWithoutArguments_returnsValidCommand() {
        Command result = Parser.parse("list");
        assertEquals("list", result.getCommand());
        assertEquals("", result.getArguments());
    }

    @Test
    public void parser_parseEmptyCommand_returnsUnknownCommand() {
        Command result = Parser.parse("");
        assertInstanceOf(UnknownCommand.class, result);
    }

    @Test
    public void parser_parseUnknownCommand_returnsUnknownCommand() {
        Command result = Parser.parse("invalid command here");
        assertInstanceOf(UnknownCommand.class, result);
        assertEquals("invalid", result.getCommand());
        assertEquals("command here", result.getArguments());
    }

    // Test all known command types
    @Test
    public void parser_parseListCommand_returnsListCommand() {
        Command result = Parser.parse("list");
        assertInstanceOf(ListCommand.class, result);
    }

    @Test
    public void parser_parseByeCommand_returnsByeCommand() {
        Command result = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, result);
    }

    @Test
    public void parser_parseMarkCommand_returnsMarkCommand() {
        Command result = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, result);
        assertEquals("1", result.getArguments());
    }

    @Test
    public void parser_parseUnmarkCommand_returnsUnmarkCommand() {
        Command result = Parser.parse("unmark 2");
        assertInstanceOf(UnmarkCommand.class, result);
        assertEquals("2", result.getArguments());
    }

    @Test
    public void parser_parseDeleteCommand_returnsDeleteCommand() {
        Command result = Parser.parse("delete 3");
        assertInstanceOf(DeleteCommand.class, result);
        assertEquals("3", result.getArguments());
    }

    @Test
    public void parser_parseFindCommand_returnsFindCommand() {
        Command result = Parser.parse("find homework");
        assertInstanceOf(FindCommand.class, result);
        assertEquals("homework", result.getArguments());
    }

    @Test
    public void parser_parseTodoCommand_returnsTodoCommand() {
        Command result = Parser.parse("todo study");
        assertInstanceOf(TodoCommand.class, result);
        assertEquals("study", result.getArguments());
    }

    @Test
    public void parser_parseDeadlineCommand_returnsDeadlineCommand() {
        Command result = Parser.parse("deadline assignment /by 2024-12-31 23:59");
        assertInstanceOf(DeadlineCommand.class, result);
        assertEquals("assignment /by 2024-12-31 23:59", result.getArguments());
    }

    @Test
    public void parser_parseEventCommand_returnsEventCommand() {
        Command result = Parser.parse("event party /from 2024-12-31 18:00 /to 2024-12-31 23:00");
        assertInstanceOf(EventCommand.class, result);
        assertEquals("party /from 2024-12-31 18:00 /to 2024-12-31 23:00", result.getArguments());
    }

    // ========== Task File Parsing Tests ==========

    @Test
    public void parser_parseTaskFromFile_returnsValidTodo() {
        Task result = Parser.parseTaskFromFile("T | 0 | buy milk");
        assertNotNull(result);
        assertInstanceOf(TodoTask.class, result);
        assertEquals("buy milk", result.getDescription());
        assertFalse(result.isDone());
    }

    @Test
    public void parser_parseTaskFromFile_returnsValidDeadline() {
        Task result = Parser.parseTaskFromFile("D | 1 | submit assignment | 2024-02-15T14:30");
        assertNotNull(result);
        assertInstanceOf(DeadlineTask.class, result);
        assertEquals("submit assignment", result.getDescription());
        assertTrue(result.isDone());
    }

    @Test
    public void parser_parseTaskFromFile_returnsValidEvent() {
        Task result = Parser.parseTaskFromFile("E | 0 | party | 2024-02-15T14:30 | 2024-02-16T14:30");
        assertNotNull(result);
        assertInstanceOf(EventTask.class, result);
        assertEquals("party", result.getDescription());
        assertFalse(result.isDone());
    }

    // ========== Edge Cases and Error Handling ==========

    @Test
    public void parser_parseInvalidTaskFromFile_returnsNull() {
        Task result = Parser.parseTaskFromFile("invalid format");
        assertNull(result);
    }

    @Test
    public void parser_parseTaskWithInsufficientParts_returnsNull() {
        Task result = Parser.parseTaskFromFile("T | 0");
        assertNull(result);
    }

    @Test
    public void parser_parseTaskWithInvalidType_returnsNull() {
        Task result = Parser.parseTaskFromFile("X | 0 | some task");
        assertNull(result);
    }

    @Test
    public void parser_parseDeadlineWithMissingDate_returnsNull() {
        Task result = Parser.parseTaskFromFile("D | 0 | deadline task");
        assertNull(result);
    }

    @Test
    public void parser_parseDeadlineWithInvalidDate_returnsNull() {
        Task result = Parser.parseTaskFromFile("D | 0 | deadline task | invalid-date");
        assertNull(result);
    }

    @Test
    public void parser_parseEventWithMissingDates_returnsNull() {
        Task result = Parser.parseTaskFromFile("E | 0 | event task | 2024-02-15T14:30");
        assertNull(result);
    }

    @Test
    public void parser_parseEventWithInvalidFromDate_returnsNull() {
        Task result = Parser.parseTaskFromFile("E | 0 | event task | invalid-date | 2024-02-16T14:30");
        assertNull(result);
    }

    @Test
    public void parser_parseEventWithInvalidToDate_returnsNull() {
        Task result = Parser.parseTaskFromFile("E | 0 | event task | 2024-02-15T14:30 | invalid-date");
        assertNull(result);
    }

    @Test
    public void parser_parseTaskWithEmptyDescription_returnsNull() {
        Task result = Parser.parseTaskFromFile("T | 0 | ");
        assertNull(result);
    }

    @Test
    public void parser_parseTaskWithSpecialCharacters_handlesCorrectly() {
        Task result = Parser.parseTaskFromFile("T | 0 | buy milk & bread @store #urgent");
        assertNotNull(result);
        assertEquals("buy milk & bread @store #urgent", result.getDescription());
    }

    @Test
    public void parser_parseTaskWithPipeInDescription_handlesCorrectly() {
        // This tests how your parser handles pipe characters within the description
        Task result = Parser.parseTaskFromFile("T | 0 | task with | pipe character");
        assertNotNull(result);
    }
}
