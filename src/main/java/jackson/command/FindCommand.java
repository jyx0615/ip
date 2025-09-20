package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;
import jackson.JacksonException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the FindCommand to find tasks containing the keyword.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param taskManager The task manager to manage the tasks.
     * @throws JacksonException If there is an error during execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        ui.printTasks(taskManager.findTasks(keyword), false);
    }
}
