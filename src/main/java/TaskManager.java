public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public void addTodoTask(String description) {
        tasks[taskCount ++] = new Todo(description);
        printAddTaskMessage();
    }

    public void addEventTask(String description, String start, String deadline) {
        tasks[taskCount ++] = new Event(description, deadline, start);
        printAddTaskMessage();
    }

    public void addDeadlineTask(String description, String deadline) {
        tasks[taskCount ++] = new Deadline(description, deadline);
        printAddTaskMessage();
    }

    public void markTask(int index) {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Marked task " + index + " as done.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int index) {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].unmark();
            System.out.println("Unmarked task " + index + ".");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
}
