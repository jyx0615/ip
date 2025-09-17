package jackson.command;

import java.time.LocalTime;
import java.time.LocalDate;

import jackson.JacksonException;
import jackson.DateTimeParser;

public class Parser {
    private static final String TODO_FORMAT = "todo <description>";
    private static final String DEADLINE_FORMAT = "deadline <description> /by <time>";
    private static final String EVENT_FORMAT = "event <description> /from <start time> /to <end time>";

    public static Command parse(String userInput) throws JacksonException {
        String[] parts = userInput.trim().split("\\s+", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
        case "todo":
            return parseTodo(argument);
        case "deadline":
            return parseDeadline(argument);
        case "event":
            return parseEvent(argument);
        case "mark":
            return new MarkCommand(parseTaskIndex(argument), true);
        case "unmark":
            return new MarkCommand(parseTaskIndex(argument), false);
        case "delete":
            return new DeleteCommand(parseTaskIndex(argument));
        case "find":
            return new FindCommand(argument);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        default:
            throw new JacksonException(JacksonException.ErrorType.UNKNOWN_COMMAND);
        }
    }

    public static int parseTaskIndex(String argument) throws JacksonException {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }
    
    private static Command parseTodo(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, TODO_FORMAT);
        }
        return new AddTodoCommand(argument);
    }

    private static Command parseDeadline(String argument) throws JacksonException {
        String[] parts = argument.split(" /by ", 2);
        if (!hasTwoParts(parts)) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, DEADLINE_FORMAT);
        }
        LocalDate byDate;
        LocalTime byTime = null;
        if (parts[1].contains(" ")) {
            String[] dateTimeParts = parts[1].split(" ", 2);
            byDate = DateTimeParser.parseDate(dateTimeParts[0]);
            byTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            byDate = DateTimeParser.parseDate(parts[1]);
        }
        return new AddDeadlineCommand(parts[0], byDate, byTime);
    }

    private static Command parseEvent(String argument) throws JacksonException {
        String[] parts = argument.split(" /from ", 2);
        if (!hasTwoParts(parts)) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, EVENT_FORMAT);
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (!hasTwoParts(timeParts)) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, EVENT_FORMAT);
        }
        LocalDate fromDate, toDate;
        LocalTime fromTime = null, toTime = null;
        if (timeParts[0].contains(" ")) {
            String[] dateTimeParts = timeParts[0].split(" ", 2);
            fromDate = DateTimeParser.parseDate(dateTimeParts[0]);
            fromTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            fromDate = DateTimeParser.parseDate(timeParts[0]);
        }

        if (timeParts[1].contains(" ")) {
            String[] dateTimeParts = timeParts[1].split(" ", 2);
            toDate = DateTimeParser.parseDate(dateTimeParts[0]);
            toTime = DateTimeParser.parseTime(dateTimeParts[1]);
        } else {
            toDate = DateTimeParser.parseDate(timeParts[1]);
        }

        return new AddEventCommand(desc, fromDate, fromTime, toDate, toTime);
    }

    private static boolean hasTwoParts(String[] args) {
        return args.length >= 2 && !args[0].isEmpty() && !args[1].isEmpty();
    }
}
