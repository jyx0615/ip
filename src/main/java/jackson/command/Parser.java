package jackson.command;

import jackson.JacksonException;

public class Parser {
    private final static String TODO_FORMAT = "todo <description>";
    private final static String DEADLINE_FORMAT = "deadline <description> /by <time>";
    private final static String EVENT_FORMAT = "event <description> /from <start time> /to <end time>";
    
    public static String[] parseCommand(String userInput) throws JacksonException {
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
        case "unmark":
        case "delete":
            return parseIndex(command, argument);
        case "list":
        case "bye":
            return new String[] {command};
        default:
            return new String[] {"invalid"};
        }
    }

    private static String[] parseTodo(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, TODO_FORMAT);
        }
        return new String[] {"todo", argument};
    }

    private static String[] parseDeadline(String argument) throws JacksonException {
        String[] parts = argument.split(" /by ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, DEADLINE_FORMAT);
        } else if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, DEADLINE_FORMAT);
        }
        return new String[] {"deadline", parts[0], parts[1]};
    }

    private static String[] parseIndex(String command, String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_INDEX);
        }
        try {
            int index = Integer.parseInt(argument);
            return new String[] {command, String.valueOf(index)};
        } catch (NumberFormatException e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }

    private static String[] parseEvent(String argument) throws JacksonException {
        String[] parts = argument.split(" /from ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION, EVENT_FORMAT);
        } else
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, EVENT_FORMAT);
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, "event <desc> /from <start> /to <end>");
        }
        return new String[] {"event", desc, timeParts[0], timeParts[1]};
    }
}