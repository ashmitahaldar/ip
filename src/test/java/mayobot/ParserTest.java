package mayobot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mayobot.commands.Command;
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

    @Test
    public void parser_parseInvalidTaskFromFile_returnsInvalidFormat() {
        Task result = Parser.parseTaskFromFile("invalid format");
        assertNull(result);
    }
}
