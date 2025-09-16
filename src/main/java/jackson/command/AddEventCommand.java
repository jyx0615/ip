package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.JacksonException;
import jackson.task.Event;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task task = new Event(description, from, to);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
