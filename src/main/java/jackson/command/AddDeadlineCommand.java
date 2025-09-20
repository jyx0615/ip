package jackson.command;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.JacksonException;
import jackson.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate byDate;
    private LocalTime byTime;

    public AddDeadlineCommand(String description, LocalDate byDate, LocalTime byTime) {
        this.description = description;
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Execute the AddDeadlineCommand.
     * @param ui The Ui object to interact with the user.
     * @param storage The Storage object to handle file operations.
     * @param taskManager The TaskManager object to manage tasks.
     * @throws JacksonException If there is an error during execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task task = new Deadline(description, byDate, byTime);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
