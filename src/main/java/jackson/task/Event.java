package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.DateTimeParser;

public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    /**
     * Returns the string representation of the Event task.
     * Format: [E][status] description (from: fromDate fromTime to: toDate toTime)
     * Example: [E][X] Team meeting (from: Aug 10 2:00 PM to: Aug 10 3:00 PM)
     * @return String representation of the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", 
            super.toString(), 
            DateTimeParser.formatDateAndTime(fromDate, fromTime), 
            DateTimeParser.formatDateAndTime(toDate, toTime));
    }

    /**
     * Returns the string representation of the Event task for file storage.
     * Format: E | status | description | fromDate fromTime | toDate toTime
     * Example: E | 1 | Team meeting | 2023-08-10 14:00 | 2023-08-10 15:00
     * @return String representation of the Event task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", 
            super.toFileString(), 
            DateTimeParser.formatDateAndTimeToFileString(fromDate, fromTime),
            DateTimeParser.formatDateAndTimeToFileString(toDate, toTime));
    }

    /**
     * Returns the type of the task as TaskType.EVENT.
     * @return TaskType.EVENT
     */
    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

    /**
     * Checks if the event is in range of the given date and time.
     * If isBefore is true, checks if the event ends before the given date and time.
     * If isBefore is false, checks if the event starts after the given date and time.
     * @param isBefore true to check if event ends before the given date and time,
     *                 false to check if event starts after the given date and time.
     * @param date the date to compare with.
     * @param time the time to compare with.
     * @return true if the event is in range, false otherwise.
     */
    @Override
    public boolean isInRange(boolean isBefore, LocalDate date, LocalTime time) {
        if (isBefore) {
            if (toDate.isBefore(date)) {
                return true;
            } else if (toDate.isEqual(date) && toTime != null &&
                time != null && toTime.isBefore(time)) {
                return true;
            }
        } else {
            if (fromDate.isAfter(date)) {
                return true;
            } else if (fromDate.isEqual(date) && fromTime != null &&
                time != null && fromTime.isAfter(time)) {
                return true;
            }
        }
        return false;
    }
}
