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
    private LocalDate fromDate, toDate;
    private LocalTime fromTime, toTime;

    public AddEventCommand(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        this.description = description;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException {
        Task task = new Event(description, fromDate, fromTime, toDate, toTime);
        taskManager.addTask(task);
        ui.printAddTaskMessage(task, taskManager.getAllTasks().size());
        storage.save(taskManager.getAllTasks());
    }
}
