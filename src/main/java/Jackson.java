public class Jackson {
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
                default:
                    echo(userInput);
                    break;
            }
        }
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
