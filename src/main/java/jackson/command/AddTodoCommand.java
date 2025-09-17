package jackson.command;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.task.Todo;
import jackson.JacksonException;

public class AddTodoCommand extends Command {
    private String description;
    
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task task = new Todo(description);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
