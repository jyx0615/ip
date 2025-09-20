package jackson.command;

public class FilterCommand extends Command {
    private enum CommandType {
        DEADLINE,
        EVENT,
    }

    private enum FilterType {
        BEFORE,
        AFTER
    }

    private CommandType type;
    private String keyword;

    public FilterCommand(CommandType type, String keyword) {
        this.type = type;
        this.keyword = keyword;
    }

    @Override
    public void execute(jackson.io.Ui ui, jackson.io.Storage storage, 
        jackson.task.TaskManager taskManager) throws jackson.JacksonException {
        ui.printFilteredTasks(taskManager.getFilteredTasks(keyword));
    }
    
}
