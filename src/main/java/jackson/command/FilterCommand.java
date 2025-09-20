package jackson.command;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.task.Task.TaskType;

public class FilterCommand extends Command {
    private TaskType type;
    private boolean isBefore;
    private LocalDate date;
    private LocalTime time;

    public FilterCommand(TaskType type, boolean isBefore, LocalDate date, LocalTime time) {
        this.type = type;
        this.isBefore = isBefore;
        this.date = date;
        this.time = time;
    }

    /**
     * Execute the FilterCommand to filter tasks by type and date/time.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to save the updated task list.
     * @param taskManager The task manager to manage the tasks.
     * @throws jackson.JacksonException If there is an error during execution.
     */
    @Override
    public void execute(jackson.io.Ui ui, jackson.io.Storage storage, 
        jackson.task.TaskManager taskManager) throws jackson.JacksonException {
        ui.printTasks(
            taskManager.getFilteredTasks(type, isBefore, date, time), 
            false
        );
    }
}
