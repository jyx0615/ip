package jackson.command;

import jackson.io.Ui;
import jackson.io.Storage;
import jackson.task.TaskManager;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
