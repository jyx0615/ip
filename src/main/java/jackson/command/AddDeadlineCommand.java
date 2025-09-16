package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.JacksonException;
import jackson.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task task = new Deadline(description, by);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
