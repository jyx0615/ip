import java.util.ArrayList;
import java.util.List;
public class Jackson {
    static List<Task> tasks = new ArrayList<>();

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

            System.out.println("--------------------------------------------");
            switch (command) {
                case "bye":
                    exit();
                    return;

                case "list":
                    list();
                    break;

                case "mark":
                    if (!argument.isEmpty()) {
                        try {
                            int index = Integer.parseInt(argument);
                            mark(index);
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
                            unmark(index);
                        } catch (NumberFormatException e) {
                            System.out.println("Please provide a valid number for unmark.");
                        }
                    } else {
                        System.out.println("Usage: unmark <number>");
                    }
                    break;

                default:
                    add(userInput);
                    break;
            }

            System.out.println("\n--------------------------------------------\n");
        }
    }

    public static void mark(int index) {
        // Mark item as done
        if (index > 0 && index <= tasks.size()) {
            Task t = tasks.get(index - 1);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + t.getDescription());
            t.markAsDone(); // Update task status
        } else {
            System.out.println("Invalid index for mark.");
        }
    }

    public static void unmark(int index) {
        // Mark item as done
        if (index > 0 && index <= tasks.size()) {
            Task t = tasks.get(index - 1);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + t.getDescription());
            t.unmark(); // Update task status
        } else {
            System.out.println("Invalid index for mark.");
        }
    }

    public static void list() {
        // List all items
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + ". " + t.showTask());
        }
    }

    public static void add(String item) {
        // Add item to the list
        System.out.println("added: " + item);
        Task task = new Task(item);
        tasks.add(task);
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
