public class TaskManager {
    private Task[] tasks = new Task[100];
    private int taskCount = 0;

    public void listTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].showTask());
        }
    }

    public void addTask(String description) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = new Task(description);
            taskCount++;
            System.out.println("added: " + description);
        } else {
            System.out.println("Task list is full.");
        }
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
