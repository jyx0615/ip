package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.DateTimeParser;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Returns the string representation of the Deadline task.
     * Format: [D][status] description (by: date time)
     * Example: [D][X] submit report (by: June 6 2023 18:00)
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateAndTime(byDate, byTime) + ")";
    }


    /**
     * Returns the string representation of the Deadline task for file storage.
     * Format: D | status | description | date time
     * Example: D | 1 | submit report | 2023-06-06 18:00
     * @return String representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + DateTimeParser.formatDateAndTimeToFileString(byDate, byTime);
    }

    /**
     * Returns the type of the task.
     * @return TaskType.DEADLINE
     */
    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

    /**
     * Checks if the task is within the specified date and time range.
     * @param isBefore true if checking for tasks before the specified date and time, false for after.
     * @param date The date to compare with.
     * @param time The time to compare with.
     * @return true if the task is within the specified range, false otherwise.
     */
    @Override
    public boolean isInRange(boolean isBefore, LocalDate date, LocalTime time) {
        if (isBefore) {
            if (byDate.isBefore(date)) {
                return true;
            } else if (byDate.isEqual(date) && byTime != null && time != null && byTime.isBefore(time)) {
                return true;
            }
        } else {
            if (byDate.isAfter(date)) {
                return true;
            } else if (byDate.isEqual(date) && byTime != null && 
                time != null && byTime.isAfter(time)) {
                return true;
            }
        }
        return false;
    }
}
