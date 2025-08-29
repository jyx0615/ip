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
        if (description.isEmpty()) {
            System.out.println("The description of a todo cannot be empty.");
            return;
        }
        tasks[taskCount ++] = new Todo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void addEventTask(String[] arguments) {
        if (arguments[0].isEmpty()) {
            System.out.println("The description of an event cannot be empty.");
            return;
        }
        String start = arguments.length > 1 ? arguments[1] : "";
        String deadline  = arguments.length > 2 ? arguments[2] : "";
        start = start.replaceFirst("^from\\s+", "").replaceAll("\\s+$", "");
        deadline = deadline.replaceFirst("^to\\s+", "").replaceAll("\\s+$", "");
        tasks[taskCount ++] = new Event(arguments[0], deadline, start);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void addDeadlineTask(String[] arguments) {
        if (arguments[0].isEmpty()) {
            System.out.println("The description of a deadline cannot be empty.");
            return;
        }
        String deadline = arguments.length > 1 ? arguments[1] : "";
        deadline = deadline.replace("by ", "");
        tasks[taskCount ++] = new Deadline(arguments[0], deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
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
}
