package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;

import jackson.DateTimeParser;

public class Event extends Task {
    protected LocalDate fromDate, toDate;
    protected LocalTime fromTime, toTime;

    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), DateTimeParser.formatDateAndTime(fromDate, fromTime), DateTimeParser.formatDateAndTime(toDate, toTime));
    }

    @Override
    public String toFileString() {
        return String.format("E | %s | %s | %s", super.toFileString(), DateTimeParser.formatDateAndTimeToFileString(fromDate, fromTime), DateTimeParser.formatDateAndTimeToFileString(toDate, toTime));
    }
}
