public class Jackson {
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        String chatbotName = "Jackson";
        // Greeting
        System.out.println("--------------------------------------------");
        System.out.printf("Hello! I'm %s.\n", chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("\n--------------------------------------------\n");

        while (true) {
            // Read user input
            String userInput = System.console().readLine();
            String[] parts = userInput.trim().split("\\s+", 2);
            String command = parts[0];
            String argument = parts.length > 1 ? parts[1] : "";
            String[] arguments = argument.split("/");

            System.out.println("--------------------------------------------");
            switch (command) {
            case "bye":
                exit();
                return;
            case "list":
                taskManager.listTasks();
                break;
            case "todo":
                taskManager.addTodoTask(argument);
                break;
            case "deadline":
                taskManager.addDeadlineTask(arguments);
                break;
            case "event":
                taskManager.addEventTask(arguments);
                break;
            case "mark":
                if (!argument.isEmpty()) {
                    try {
                        int index = Integer.parseInt(argument);
                        taskManager.markTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid number for mark.");
                    }
                } else {
                    System.out.println("Usage: mark <number>");
                }
                break;
            case "unmark":
                if (!argument.isEmpty()) {
                    try {
                        int index = Integer.parseInt(argument);
                        taskManager.unmarkTask(index);
                    } catch (NumberFormatException e) {
                        System.out.println("Please provide a valid number for unmark.");
                    }
                } else {
                    System.out.println("Usage: unmark <number>");
                }
                break;
            default:
                echo("I'm sorry, but I don't know what that means :-(");
                break;
            }

            System.out.println("\n--------------------------------------------\n");
        }
    }

    public static void echo(String message) {
        // Echo the message
        System.out.println(message);
    }

    public static void exit() {
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
