
package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskManager tasksManager) {
        ui.printTasks(tasksManager.getAllTasks());
    }
}
