package jackson.command;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.Task;
import jackson.task.TaskManager;
import jackson.JacksonException;
import jackson.task.Event;

public class AddEventCommand extends Command {
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

    public AddEventCommand(String description, LocalDate fromDate,
        LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        this.description = description;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    /**
     * Execute the AddEventCommand to add an Event task.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param taskManager The task manager to manage the tasks.
     * @throws JacksonException If there is an error during execution.
     */
    @Override
    public void execute(Ui ui, Storage storage, 
        TaskManager taskManager) throws JacksonException {
        Task task = new Event(description, fromDate, fromTime, toDate, toTime);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
