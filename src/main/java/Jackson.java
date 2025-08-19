import java.util.ArrayList;
import java.util.List;

public class Jackson {
    static List<String> lists = new ArrayList<>();

    public static void main(String[] args) {
        String chatbotName = "Jackson";
        // Greeting
        System.out.println("--------------------------------------------");
        System.out.printf("Hello! I'm %s.\n", chatbotName);
        System.out.println("What can I do for you?");
        System.out.println("\n--------------------------------------------\n");

        while(true) {
            // Read user input
            String userInput = System.console().readLine();
            switch (userInput) {
                case "bye":
                    exit();
                    return;
                case "list":
                    list();
                    break;
                default:
                    add(userInput);
                    break;
            }
        }
    }

    public static void list() {
        // List all items
        System.out.println("--------------------------------------------");
        for (int i = 0; i < lists.size(); i++) {
            System.out.println((i + 1) + ". " + lists.get(i));
        }
        System.out.println("\n--------------------------------------------\n");
    }

    public static void add(String item) {
        // Add item to the list
        System.out.println("--------------------------------------------");
        System.out.println("added: " + item);
        lists.add(item);
        System.out.println("\n--------------------------------------------\n");
    }

    public static void echo(String message) {
        // Echo the message
        System.out.println("--------------------------------------------");
        System.out.println(message);
        System.out.println("\n--------------------------------------------\n");
    }

    public static void exit() {
        // Exit
        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\n--------------------------------------------\n");

    }
}
