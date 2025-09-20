
package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;

public class ListCommand extends Command {
    /**
     * Execute the ListCommand to list all tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param tasksManager The task manager to manage the tasks.
     * @throws jackson.JacksonException If there is an error during execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskManager tasksManager) {
        ui.printTasks(tasksManager.getAllTasks(), true);
    }
}
