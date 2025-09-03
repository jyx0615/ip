public class Parser {

    public static String[] parseCommand(String userInput) {
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

    private static String[] parseTodo(String argument) {
        if (argument.isEmpty()) {
            showUsageMsg("todo <desc>");
            return new String[] {"invalid"};
        }
        return new String[] {"todo", argument};
    }

    private static String[] parseDeadline(String argument) {
        String[] parts = argument.split(" /by ", 2);
        if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            showUsageMsg("deadline <desc> /by <time>");
            return new String[] {"invalid"};
        }
        return new String[] {"deadline", parts[0], parts[1]};
    }

    private static String[] parseEvent(String argument) {
        String[] parts = argument.split(" /from ", 2);
        if (parts.length < 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            showUsageMsg("event <desc> /from <start> /to <end>");
            return new String[] {"invalid"};
        }
        String desc = parts[0];
        String[] timeParts = parts[1].split(" /to ", 2);
        if (timeParts.length < 2 || timeParts[0].isEmpty() || timeParts[1].isEmpty()) {
            showUsageMsg("event <desc> /from <start> /to <end>");
            return new String[] {"invalid"};
        }
        return new String[] {"event", desc, timeParts[0], timeParts[1]};
    }

    private static void showUsageMsg(String msg) {
        System.out.println("Usage: " + msg);
    }
}