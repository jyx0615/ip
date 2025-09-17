package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.JacksonException;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task t = taskManager.get(taskIndex);
        taskManager.deleteTask(t);
        ui.printDeleteTaskMessage(t, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
