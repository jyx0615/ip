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

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.formatDateAndTime(byDate, byTime) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + DateTimeParser.formatDateAndTimeToFileString(byDate, byTime);
    }

    @Override
    public TaskType getType() {
        return TaskType.DEADLINE;
    }

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
