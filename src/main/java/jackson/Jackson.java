package jackson;

import java.nio.file.Path;
import java.nio.file.Paths;

import jackson.command.Parser;
import jackson.io.Storage;
import jackson.io.Ui;
import jackson.task.TaskManager;
import jackson.command.Command;

public class Jackson {
    private static final String DATA_DIRECTORY = "data";
    private static final String DATA_FILE = "jackson.txt";
    private static final Path DATA_PATH = Paths.get(DATA_DIRECTORY, DATA_FILE);
    private static TaskManager taskManager;
    private static Ui ui;
    private static Storage storage;

    public Jackson(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskManager = new TaskManager(storage.load());
        } catch (JacksonException e) {
            ui.showLoadingError();
            taskManager = new TaskManager(); // init an empty task list
        }
    }

    /**
     * The main entry point of the application.
     * @param args
     */
    public static void main(String[] args) {
        new Jackson(DATA_PATH.toString()).run();
    }

    private void run() {
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printBreakingLine();
                Command c = Parser.parse(fullCommand);
                c.execute(ui, storage, taskManager);
                isExit = c.isExit();
            } catch (JacksonException e) {
                ui.printErrorMessage(e.getMessage());
            } finally {
                ui.printBreakingLine();
            }
        }
    }
}
