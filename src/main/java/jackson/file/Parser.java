package jackson.file;

import jackson.JacksonException;
import jackson.task.TaskManager;
import jackson.task.Task;

public class Parser {
    private static TaskManager taskManager;

    public static void setTaskManager(TaskManager tm) {
        taskManager = tm;
    }

    public static void parseTask(String line) throws JacksonException {
        System.out.println("Parsing task from line: " + line);
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            parseTodoTask(parts, isDone);
            break;
        case "D":
            parseDeadlineTask(parts, isDone);
            break;
        case "E":
            parseEventTask(parts, isDone);
            break;
        default:
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
    }

    private static void parseTodoTask(String[] parts, boolean isDone) throws JacksonException {
        if (parts.length < 3) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = taskManager.addTodoTask(parts[2]);
        if (isDone) {
            t.markAsDone();
        }
    }

    private static void parseDeadlineTask(String[] parts, boolean isDone) throws JacksonException {
        if (parts.length < 4) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = taskManager.addDeadlineTask(parts[2], parts[3]);
        if (isDone) {
            t.markAsDone();
        }
    }

    private static void parseEventTask(String[] parts, boolean isDone) throws JacksonException {
        if (parts.length < 4) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = taskManager.addEventTask(parts[2], parts[3], parts[4]);
        if (isDone) {
            t.markAsDone();
        }
    }
}
