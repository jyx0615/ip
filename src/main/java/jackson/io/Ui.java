package jackson.io;

import java.util.List;
import java.util.Scanner;

import jackson.task.Task;

public class Ui {
    private static final String CHAT_BOT_NAME = "Jackson";
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Print error message to the user.
     * @param message
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Print a breaking line to separate different sections of output.
     */
    public void printBreakingLine() {
        System.out.println("\n--------------------------------------------");
    }

    /**
     * Print the welcome message when the program starts.
     */
    public static void printWelcomeMessage() {
        System.out.println("--------------------------------------------");
        System.out.printf("Hello! I'm %s.\n", CHAT_BOT_NAME);
        System.out.println("     ____.              __                         \n" + //
                "    |    |____    ____ |  | __  __________   ____  \n" + //
                "    |    \\__  \\ _/ ___\\|  |/ / /  ___/  _ \\ /    \\ \n" + //
                "/\\__|    |/ __ \\\\  \\___|    <  \\___ (  <_> )   |  \\\n" + //
                "\\________(____  /\\___  >__|_ \\/____  >____/|___|  /\n" + //
                "              \\/     \\/     \\/     \\/           \\/ ");
        System.out.println("What can I do for you?");
        System.out.println("\n--------------------------------------------");
    }

    /**
     * Print message after adding a task.
     * @param task
     * @param tasksSize
     */
    public void printAddTaskMessage(Task task, int tasksSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Print message after deleting a task.
     * @param task
     * @param tasksSize
     */
    public void printDeleteTaskMessage(Task task, int tasksSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    /**
     * Print the list of tasks.
     * @param tasks List of tasks to print
     * @param isAll true if printing all tasks, false if printing search results
     */
    public void printTasks(List<Task> tasks, boolean isAll) {
        if (isAll) {
            System.out.println("Here are the tasks in your list:");
        } else {
            System.out.println("Here are the matching tasks in your list:");
        }
        tasks.stream()
            .forEach(t -> System.out.println((tasks.indexOf(t) + 1) + ". " + t.toString()));
    }

    /**
     * Print message after marking a task as done.
     * @param task
     */
    public void printMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Print message after unmarking a task as not done.
     * @param task
     */
    public void printUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    /**
     * Print the exit message when the program ends.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print message when loading tasks from file fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    /**
     * Read the command input by the user.
     * @return The command input by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
