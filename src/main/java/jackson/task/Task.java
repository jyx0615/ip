package jackson.task;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return isDone;
    }
    
    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
