package jackson.command;

import jackson.JacksonException;

public class Parser {
    
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
            return new String[] {command, argument};
        case "list":
        case "bye":
            return new String[] {command};
        default:
            return new String[] {"invalid"};
        }
    }

    private static String[] parseTodo(String argument) throws JacksonException {
        if (argument.isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION);
        }
        return new String[] {"todo", argument};
    }

    private static String[] parseDeadline(String argument) throws JacksonException {
        String[] parts = argument.split(" /by ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION);
        } else if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, "deadline <desc> /by <time>");
        }
        return new String[] {"deadline", parts[0], parts[1]};
    }

    private static String[] parseEvent(String argument) throws JacksonException {
        String[] parts = argument.split(" /from ", 2);
        if (parts[0].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.EMPTY_TASK_DESCRIPTION);
        } else
        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, "event <desc> /from <start> /to <end>");
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
            throw new JacksonException(JacksonException.ErrorType.INVIALID_TASK_FORMAT, "event <desc> /from <start> /to <end>");
        }
        return new String[] {"event", desc, timeParts[0], timeParts[1]};
    }
}