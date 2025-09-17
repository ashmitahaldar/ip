package mayobot.commands;

import java.util.ArrayList;

import mayobot.exceptions.MayoBotException;
import mayobot.task.Task;
import mayobot.task.TaskList;
import mayobot.ui.Ui;

/**
 * Command to search for tasks containing a specific keyword or phrase.
 * <p>
 * This command performs a case-insensitive search through all tasks in the task list,
 * matching tasks whose descriptions contain the specified search term. The search
 * results include the original task indices and display the matching tasks in a
 * numbered list format.
 * <p>
 * Usage: {@code find <search_term>}
 * <p>
 * Example: {@code find meeting} - finds all tasks containing the word "meeting"
 */
public class FindCommand extends Command {
    private static final String LIST_OUTPUT_HEADER = "Here are the matching tasks in your list:";
    private static final String MISSING_SEARCH_TERM_MESSAGE = "Please specify a search term.";
    private static final String NO_TASK_FOUND_MESSAGE = "No matching tasks found.";

    private static final String FUZZY_SEARCH_INDICATOR = " (fuzzy search)";
    private static final String STRICT_SEARCH_INDICATOR = " (exact word search)";

    private static final double FUZZY_THRESHOLD = 0.7; // 70% similarity threshold

    private final boolean fuzzySearch;
    private final boolean strictSearch;
    private final String searchTerm;

    /**
     * Constructs a new FindCommand with the specified search arguments.
     *
     * @param arguments the search term or phrase to look for in task descriptions
     */
    public FindCommand(String arguments) {
        super("find", arguments);

        String trimmedArgs = arguments.trim();

        this.fuzzySearch = trimmedArgs.startsWith("--fuzzy");
        this.strictSearch = trimmedArgs.startsWith("--strict");

        // Extract search term by removing flags
        if (fuzzySearch) {
            this.searchTerm = trimmedArgs.replaceFirst("^--fuzzy\\s*", "").trim();
        } else if (strictSearch) {
            this.searchTerm = trimmedArgs.replaceFirst("^--strict\\s*", "").trim();
        } else {
            this.searchTerm = trimmedArgs;
        }
    }

    /**
     * Executes the find command to search for tasks containing the specified term.
     * <p>
     * Validates that a search term is provided, performs a search through the task list,
     * and displays the results. If no search term is provided, prompts the user to
     * specify one. If no matches are found, informs the user accordingly.
     *
     * @param ui the user interface handler for displaying messages
     * @param taskList the task list to search through
     * @param isGui true if running in GUI mode, false for CLI mode
     * @return formatted response message containing search results for GUI mode,
     *         or search prompt/no results message if applicable
     * @throws MayoBotException if an error occurs during task searching
     */
    @Override
    public String execute(Ui ui, TaskList taskList, boolean isGui) throws MayoBotException {
        if (searchTerm.isEmpty()) {
            String helpMessage = buildHelpMessage();
            if (!isGui) {
                ui.showMessage(helpMessage);
            }
            return buildResponse(helpMessage);
        }

        ArrayList<Object[]> matchingTasks = performSearch(taskList);

        if (matchingTasks.isEmpty()) {
            String noResultsMessage = buildNoResultsMessage();
            if (!isGui) {
                ui.showMessage(noResultsMessage);
            }
            return buildResponse(noResultsMessage);
        }

        return buildSearchResults(ui, matchingTasks, isGui);
    }

    /**
     * Performs the appropriate search based on the search mode.
     */
    private ArrayList<Object[]> performSearch(TaskList taskList) {
        if (fuzzySearch) {
            return taskList.findTasksFuzzy(searchTerm, FUZZY_THRESHOLD);
        } else if (strictSearch) {
            return taskList.findTasksStrict(searchTerm);
        } else {
            return taskList.findTasks(searchTerm); // Your existing partial match method
        }
    }

    /**
     * Builds help message for invalid search input.
     */
    private String buildHelpMessage() {
        return MISSING_SEARCH_TERM_MESSAGE + "\n"
                + "Usage:\n"
                + "  find <search_term> - partial matching\n"
                + "  find --fuzzy <search_term> - fuzzy matching (handles typos)\n"
                + "  find --strict <search_term> - exact word matching";
    }

    /**
     * Builds no results message with search mode indicator.
     */
    private String buildNoResultsMessage() {
        String searchMode = "";
        if (fuzzySearch) {
            searchMode = FUZZY_SEARCH_INDICATOR;
        } else if (strictSearch) {
            searchMode = STRICT_SEARCH_INDICATOR;
        }

        return NO_TASK_FOUND_MESSAGE + " for \"" + searchTerm + "\"" + searchMode;
    }

    /**
     * Builds and returns the search results response.
     */
    private String buildSearchResults(Ui ui, ArrayList<Object[]> matchingTasks, boolean isGui) {
        StringBuilder response = new StringBuilder();
        String header = buildSearchHeader(matchingTasks.size());

        if (!isGui) {
            ui.showMessage(header);
        }
        response.append(header).append("\n");

        for (Object[] matchingTask : matchingTasks) {
            int index = (Integer) matchingTask[0];
            Task task = (Task) matchingTask[1];
            String taskLine = index + ". " + task;

            if (!isGui) {
                ui.showMessage(taskLine);
            }
            response.append(taskLine).append("\n");
        }

        return buildResponse(response.toString());
    }

    /**
     * Builds the search results header with search mode indicator.
     */
    private String buildSearchHeader(int resultCount) {
        String searchMode = "";
        if (fuzzySearch) {
            searchMode = FUZZY_SEARCH_INDICATOR;
        } else if (strictSearch) {
            searchMode = STRICT_SEARCH_INDICATOR;
        }

        return LIST_OUTPUT_HEADER + " (" + resultCount + " found)" + searchMode;
    }
}
