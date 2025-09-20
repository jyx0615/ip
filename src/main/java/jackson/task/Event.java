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

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", 
            super.toString(), 
            DateTimeParser.formatDateAndTime(fromDate, fromTime), 
            DateTimeParser.formatDateAndTime(toDate, toTime));
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", 
            super.toFileString(), 
            DateTimeParser.formatDateAndTimeToFileString(fromDate, fromTime),
            DateTimeParser.formatDateAndTimeToFileString(toDate, toTime));
    }

    @Override
    public TaskType getType() {
        return TaskType.EVENT;
    }

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
