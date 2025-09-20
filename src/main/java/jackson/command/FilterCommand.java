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

    @Override
    public void execute(jackson.io.Ui ui, jackson.io.Storage storage, 
        jackson.task.TaskManager taskManager) throws jackson.JacksonException {
        System.out.printf("%s %b %s %s\n", type, isBefore, date, time);
        ui.printTasks(
            taskManager.getFilteredTasks(type, isBefore, date, time), 
            false
        );
    }
}
