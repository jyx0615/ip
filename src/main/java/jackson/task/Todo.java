package jackson.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     * Format: [T][status] description
     * Example: [T][X] read book
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task for file storage.
     * Format: T | status | description
     * Example: T | 1 | read book
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
