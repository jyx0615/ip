package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;
public abstract class Task {
    private String description;
    private boolean isDone;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the status of the task.
     * @return True if the task is done, false otherwise.
     */
    public Boolean getStatus() {
        return isDone;
    }
    
    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmark the task.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Return the string representation of the task.
     * @return The string representation of the task.
     */
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Return the string representation of the task for saving to file.
     * @return The string representation of the task for saving to file.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Get the type of the task.
     * @return TaskType of the task.
     */
    public TaskType getType() {
        return TaskType.TODO;
    }

    /**
     * Check if the task is in the given date and time range.
     * @param isBefore True if checking for tasks before the given date and time, false otherwise.
     * @param date The date to check against.
     * @param time The time to check against.
     * @return True if the task is in the given date and time range, false otherwise.
     */
    public boolean isInRange(boolean isBefore, LocalDate date, LocalTime time) {
        return false;
    }
}
