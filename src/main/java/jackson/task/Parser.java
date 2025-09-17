package jackson.task;

import jackson.JacksonException;

public class Parser {
    private static TaskManager taskManager;

    public static void setTaskManager(TaskManager tm) {
        taskManager = tm;
    }

    public static void parseTask(String line, TaskManager taskManager) throws JacksonException {
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
        Task t = new Todo(parts[2]);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }

    private static void parseDeadlineTask(String[] parts, boolean isDone) throws JacksonException {
        if (parts.length < 4) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = new Deadline(parts[2], parts[3]);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }

    private static void parseEventTask(String[] parts, boolean isDone) throws JacksonException {
        if (parts.length < 4) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = new Event(parts[2], parts[3], parts[4]);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }
}
