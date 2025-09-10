package jackson;
import java.util.Scanner;

import jackson.command.Parser;
import jackson.task.TaskManager;

public class Jackson {
    private static final String CHAT_BOT_NAME = "Jackson";
    private static TaskManager taskManager = new TaskManager();
    
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            // Read user input
            String userInput = SCANNER.nextLine();
            showBreakingLine();
            try {
                String[] userCommands = Parser.parseCommand(userInput);
                executeCommand(userCommands);
            } catch (JacksonException e) {
                System.out.println(e.getMessage());
            }
            
            showBreakingLine();
        }
    }

    private static void showBreakingLine() {
        System.out.println("\n--------------------------------------------");
    }

    private static void executeCommand(String[] userCommands) throws JacksonException {
        switch (userCommands[0]) {
        case "bye":
            exit();
            System.exit(0);
            break;
        case "list":
            taskManager.listTasks();
            break;
        case "todo":
            taskManager.addTodoTask(userCommands[1]);
            break;
        case "deadline":
            taskManager.addDeadlineTask(userCommands[1], userCommands[2]);
            break;
        case "event":
            taskManager.addEventTask(userCommands[1], userCommands[2], userCommands[3]);
            break;
        case "mark":
            taskManager.markTask(Integer.parseInt(userCommands[1]));
            break;
        case "unmark": 
            taskManager.unmarkTask(Integer.parseInt(userCommands[1]));
            break;
        case "delete":
            taskManager.deleteTask(Integer.parseInt(userCommands[1]));
            break;
        default:
            throw new JacksonException(JacksonException.ErrorType.UNKNOWN_COMMAND);
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("--------------------------------------------");
        System.out.printf("Hello! I'm %s.\n", CHAT_BOT_NAME);
        System.out.println("     ____.              __                         \n" + //
                        "    |    |____    ____ |  | __  __________   ____  \n" + //
                        "    |    \\__  \\ _/ ___\\|  |/ / /  ___/  _ \\ /    \\ \n" + //
                        "/\\__|    |/ __ \\\\  \\___|    <  \\___ (  <_> )   |  \\\n" + //
                        "\\________(____  /\\___  >__|_ \\/____  >____/|___|  /\n" + //
                        "              \\/     \\/     \\/     \\/           \\/ ");
        System.out.println("What can I do for you?");
        showBreakingLine();
    }

    public static void exit() {
        // Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
