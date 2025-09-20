package jackson.command;

import jackson.JacksonException;
import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;

public class Command {
    public void execute(Ui ui, Storage storage, TaskManager taskManager) throws JacksonException{
        // Implementation of command execution
    }

    /**
     * Indicates whether this command is an exit command.
     * 
     * @return true if this command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false; // Default implementation
    }
}
