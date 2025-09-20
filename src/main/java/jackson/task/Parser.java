package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.JacksonException;
import jackson.DateTimeParser;

public class Parser {
    /**
     * Parses a line from the storage file and adds the corresponding task to the task manager.
     * @param line
     * @param taskManager
     * @throws JacksonException
     */
    public static void parseTask(String line, TaskManager taskManager) throws JacksonException {
        String[] parts = line.split(" \\| ");
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            parseTodoTask(parts, isDone, taskManager);
            break;
        case "D":
            parseDeadlineTask(parts, isDone, taskManager);
            break;
        case "E":
            parseEventTask(parts, isDone, taskManager);
            break;
        default:
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
    }

    private static void parseTodoTask(String[] parts, boolean isDone,
        TaskManager taskManager) throws JacksonException {
        if (parts.length < 3) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        Task t = new Todo(parts[2]);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }

    private static void parseDeadlineTask(String[] parts, boolean isDone,
        TaskManager taskManager) throws JacksonException {
        if (parts.length != 4) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        LocalDate byDate;
        LocalTime byTime = null;
        if (parts[3].contains(" ")) {
            String[] dateTimeParts = parts[3].split(" ", 2);
            byDate = DateTimeParser.parseDate(dateTimeParts[0]);
            byTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            byDate = DateTimeParser.parseDate(parts[3]);
        }
        Task t = new Deadline(parts[2], byDate, byTime);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }

    private static void parseEventTask(String[] parts, boolean isDone,
        TaskManager taskManager) throws JacksonException {
        if (parts.length != 5) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_FILE_FORMAT);
        }
        LocalDate fromDate;
        LocalDate toDate;
        LocalTime fromTime = null;
        LocalTime toTime = null;

        if (parts[3].contains(" ")) {
            String[] dateTimeParts = parts[3].split(" ", 2);
            fromDate = DateTimeParser.parseDate(dateTimeParts[0]);
            fromTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            fromDate = DateTimeParser.parseDate(parts[3]);
        }

        if (parts[4].contains(" ")) {
            String[] dateTimeParts = parts[4].split(" ", 2);
            toDate = DateTimeParser.parseDate(dateTimeParts[0]);
            toTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            toDate = DateTimeParser.parseDate(parts[4]);
        }
        
        Task t = new Event(parts[2], fromDate, fromTime, toDate, toTime);
        taskManager.addTask(t);
        taskManager.markTask(t, isDone);
    }
}
