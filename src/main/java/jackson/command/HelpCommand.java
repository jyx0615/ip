package jackson.command;

import jackson.io.Ui;
import jackson.io.Storage;
import jackson.task.TaskManager;

public class HelpCommand extends Command{
    /**
     * Execute the HelpCommand to display help information.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param taskManager The task manager to manage the tasks.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) {
        ui.showHelpMessage();
    }
}
