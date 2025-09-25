package jackson.command;

import java.time.LocalTime;
import java.time.LocalDate;

import jackson.JacksonException;
import jackson.DateTimeParser;
import jackson.task.Task.TaskType;

public class Parser {
    private static final String TODO_FORMAT = "todo <description>";
    private static final String DEADLINE_FORMAT = 
        "deadline <description> /by <date and time>";
    private static final String EVENT_FORMAT = 
        "event <description> /from <start date and time> /to <end date and time>";
    private static final String LIST_FORMAT = 
        "list\nlist deadline/event before/after <date> [time]";
    private static final String FIND_FORMAT = "find <keyword>";

    /**
     * Parse the user input and return the corresponding Command object.
     * 
     * @param userInput The user input string.
     * @return The Command object corresponding to the user input.
     * @throws JacksonException If the command is unknown or the format is invalid.
     */
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
            return parseFind(argument);
        case "list":
            return parseList(argument);
        case "bye":
        case "exit":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        default:
            throw new JacksonException(JacksonException.ErrorType.UNKNOWN_COMMAND);
        }
    }
    
    private static Command parseFind(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_COMMAND_FORMAT, FIND_FORMAT);
        }
        return new FindCommand(argument);
    }

    private static Command parseList(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            return new ListCommand();
        }
        String[] parts = argument.split(" ", 4);
        if (parts.length < 3) {
            throw new JacksonException(
                JacksonException.ErrorType.INVALID_COMMAND_FORMAT, 
                LIST_FORMAT
            );
        }
        String filterType = parts[0];
        TaskType type;
        switch (filterType) {
        case "deadline":
            type = TaskType.DEADLINE;
            break;
        case "event":
            type = TaskType.EVENT;
            break;
        default:
            throw new JacksonException(
                JacksonException.ErrorType.INVALID_COMMAND_FORMAT, 
                LIST_FORMAT
            );
        }

        String filterKeyword = parts[1];
        boolean isBefore = false;
        switch(filterKeyword) {
        case "before":
            isBefore = true;
            break;
        case "after":
            isBefore = false;
            break;
        default:
            throw new JacksonException(
                JacksonException.ErrorType.INVALID_COMMAND_FORMAT, 
                LIST_FORMAT
            );
        }

        LocalDate date = DateTimeParser.parseDate(parts[2]);;
        LocalTime time = null;
        if (parts.length == 4) {
            time = DateTimeParser.parseTime(parts[3]);
        }

        return new FilterCommand(type, isBefore, date, time);
    }

    private static int parseTaskIndex(String argument) throws JacksonException {
        try {
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }

    private static Command parseTodo(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_COMMAND_FORMAT, TODO_FORMAT);
        }
        return new AddTodoCommand(argument);
    }

    private static Command parseDeadline(String argument) throws JacksonException {
        String[] parts = argument.split(" /by ", 2);
        if (!hasTwoParts(parts)) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_COMMAND_FORMAT, DEADLINE_FORMAT);
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
            throw new JacksonException(JacksonException.ErrorType.INVALID_COMMAND_FORMAT, EVENT_FORMAT);
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (!hasTwoParts(timeParts)) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_COMMAND_FORMAT, EVENT_FORMAT);
        }
        LocalDate fromDate;
        LocalDate toDate;
        LocalTime fromTime = null;
        LocalTime toTime = null;
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

        if (toDate.isBefore(fromDate) || 
            (toDate.isEqual(fromDate) && fromTime != null 
            && toTime != null && toTime.isBefore(fromTime))) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_EVENT_TIME);
        }
        return new AddEventCommand(desc, fromDate, fromTime, toDate, toTime);
    }

    private static boolean hasTwoParts(String[] args) {
        return args.length >= 2 && !args[0].isEmpty() && !args[1].isEmpty();
    }
}
