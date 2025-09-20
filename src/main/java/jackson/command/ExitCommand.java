package jackson.command;

import jackson.io.Ui;
import jackson.io.Storage;
import jackson.task.TaskManager;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) {
        ui.showExitMessage();
    }

    /**
     * Indicates that this command is an exit command.
     * @return true, as this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
