package jackson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeParser {
    private static Locale sgLocale = Locale.forLanguageTag("en-SG");

    /**
     * Formats date and time to a string suitable for saving to file.
     * @param date
     * @param time
     * @return Formatted date and time string.
     */
    public static String formatDateAndTimeToFileString(LocalDate date, LocalTime time) {
        return date.toString() + (time != null ? " " + time.toString() : "");
    }

    /**
     * Formats date and time to a string suitable for displaying to user.
     * @param date
     * @param time
     * @return Formatted date and time string.
     */
    public static String formatDateAndTime(LocalDate date, LocalTime time) {
        return formatDate(date) + (time != null ? " " + formatTime(time) : "");
    }

    private static String formatDate(LocalDate date) {
        return date.format(
            DateTimeFormatter
                .ofPattern("MMM d yyyy")
                .withLocale(sgLocale)
        );
    }

    private static String formatTime(LocalTime time) {
        return time.format(
            DateTimeFormatter
                .ofPattern("hh:mm a")
                .withLocale(sgLocale)
        );
    }

    /**
     * Parses a date string in the format YYYY-MM-DD to a LocalDate object.
     * @param dateStr
     * @return Parsed LocalDate object.
     * @throws JacksonException
     */
    public static LocalDate parseDate(String dateStr) throws JacksonException {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_DATE_FORMAT);
        }
    }

    /**
     * Parses a time string in the format HH:MM (24-hour) to a LocalTime object.
     * @param timeStr
     * @return Parsed LocalTime object.
     * @throws JacksonException
     */
    public static LocalTime parseTime(String timeStr) throws JacksonException {
        try {
            return LocalTime.parse(timeStr);
        } catch (Exception e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TIME_FORMAT);
        }
    }
}
