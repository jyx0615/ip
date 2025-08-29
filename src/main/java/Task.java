public class Task {
    private String description;
    private boolean isDone;
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private TaskType type;
    private String start;
    private String deadline;

    public Task(String description, TaskType type) {
        this(description, type, null, null);
    }

    public Task(String description, TaskType type, String deadline) {
        this(description, type, deadline, null);
    }

    public Task(String description, TaskType type, String deadline, String start) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        this.deadline = deadline;
        this.start = start;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return isDone;
    }

    public TaskType getType() {
        return type;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStart() {
        return start;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String showTaskText() {
        switch(type) {
        case TODO:
            return (isDone ? "[T][X] " : "[T][ ] ") + description;
        case DEADLINE:
            return String.format("[D]%s %s (by: %s)",
                isDone ? "[X]" : "[ ]",
                description,
                deadline != null ? deadline : ""
            );
        case EVENT:
            return String.format("[E]%s %s (from: %s to: %s)",
                isDone ? "[X]" : "[ ]",
                description,
                start != null ? start : "",
                deadline != null ? deadline : ""
            );
        default:
            return description;
        }
    }
}
