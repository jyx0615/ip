package jackson.command;

import jackson.JacksonException;

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
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, TODO_FORMAT);
        }
        return new AddTodoCommand(argument);
    }

    private static Command parseDeadline(String argument) throws JacksonException {
        String[] parts = argument.split(" /by ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, DEADLINE_FORMAT);
        } else if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, DEADLINE_FORMAT);
        }
        return new AddDeadlineCommand(parts[0], parts[1]);
    }

    private static Command parseEvent(String argument) throws JacksonException {
        String[] parts = argument.split(" /from ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, EVENT_FORMAT);
        } else if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, EVENT_FORMAT);
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
            throw new JacksonException(
                JacksonException.ErrorType.INVIALID_TASK_FORMAT, 
                "event <desc> /from <start> /to <end>"
            );
        }
        return new AddEventCommand(desc, timeParts[0], timeParts[1]);
    }
}
