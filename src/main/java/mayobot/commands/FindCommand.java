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
    /**
     * Constructs a new FindCommand with the specified search arguments.
     *
     * @param arguments the search term or phrase to look for in task descriptions
     */
    public FindCommand(String arguments) {
        super("find", arguments);
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
        String arguments = this.getArguments();
        if (arguments.trim().isEmpty()) {
            if (!isGui) {
                ui.showMessage("Please specify a search term.");
            }
            return buildResponse("Please specify a search term.");
        } else {
            ArrayList<Object[]> matchingTasks = taskList.findTask(arguments.trim());
            if (matchingTasks.isEmpty()) {
                if (!isGui) {
                    ui.showMessage("No matching tasks found.");
                }
                return buildResponse("No matching tasks found.");
            } else {
                StringBuilder response = new StringBuilder();
                if (!isGui) {
                    ui.showMessage("Here are the matching tasks in your list:");
                }
                response.append("Here are the matching tasks in your list:\n");
                for (Object[] matchingTask : matchingTasks) {
                    int index = (Integer) matchingTask[0];
                    Task task = (Task) matchingTask[1];
                    if (!isGui) {
                        ui.showMessage(index + ". " + task);
                    }
                    response.append(index + ". " + task + "\n");
                }
                return buildResponse(response.toString());
            }
        }
    }
}
