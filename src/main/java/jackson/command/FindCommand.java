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

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        ui.printTasks(taskManager.findTasks(keyword), false);
    }
}
