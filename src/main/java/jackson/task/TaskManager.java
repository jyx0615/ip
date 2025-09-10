package jackson.task;
import java.util.ArrayList;

import jackson.JacksonException;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void addTodoTask(String description) throws JacksonException {
        tasks.add(new Todo(description));
        printAddTaskMessage();
    }

    public void addEventTask(String description, String start, String deadline) throws JacksonException{
        tasks.add(new Event(description, start, deadline));
        printAddTaskMessage();
    }

    public void addDeadlineTask(String description, String deadline) throws JacksonException {
        tasks.add(new Deadline(description, deadline));
        printAddTaskMessage();
    }

    public void markTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            System.out.println("Marked task " + index + " as done.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            tasks.get(index - 1).unmark();
            System.out.println("Unmarked task " + index + ".");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void deleteTask(int index) {
        if (index > 0 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
