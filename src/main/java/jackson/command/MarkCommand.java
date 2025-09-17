package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.JacksonException;
import jackson.task.TaskManager;

public class MarkCommand extends Command {
    private int taskIndex;
    private boolean isMark; // true for mark, false for unmark

    public MarkCommand(int taskIndex, boolean isMark) {
        this.taskIndex = taskIndex;
        this.isMark = isMark;
    }

    @Override
    public void execute(Ui ui, Storage storage,TaskManager taskManager) throws JacksonException {
        Task t = taskManager.get(taskIndex);
        if (isMark) {
            taskManager.markTask(t, true);
            ui.printMarkTaskMessage(t);
        } else {
            taskManager.markTask(t, false);
            ui.printUnmarkTaskMessage(t);
        }
        storage.save(taskManager.getAllTasks());
    }
}
