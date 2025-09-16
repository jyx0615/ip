package jackson.io;

import java.util.ArrayList;
import java.util.Scanner;

import jackson.task.Task;

public class Ui {
    private static final String CHAT_BOT_NAME = "Jackson";
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printBreakingLine() {
        System.out.println("\n--------------------------------------------");
    }

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

    public void printAddTaskMessage(Task task, int tasksSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    public void printDeleteTaskMessage(Task task, int tasksSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksSize + " tasks in the list.");
    }

    public void printTasks(ArrayList<Task> tasks, boolean isAll) {
        if (isAll) {
            System.out.println("Here are the tasks in your list:");
        } else {
            System.out.println("Here are the matching tasks in your list:");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void printMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void printUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with an empty task list.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
