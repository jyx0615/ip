package jackson.command;

import jackson.JacksonException;
import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;

public class Command {
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException{
        // Implementation of command execution
    }

    public boolean isExit() {
        return false; // Default implementation
    }
}
