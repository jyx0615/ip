package jackson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeParser {
    private static Locale sgLocale = Locale.forLanguageTag("en-SG");

    public static String formatDateAndTimeToFileString(LocalDate date, LocalTime time) {
        return date.toString() + (time != null ? " " + time.toString() : "");
    }

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

    public static LocalDate parseDate(String dateStr) throws JacksonException {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_DATE_FORMAT);
        }
    }

    public static LocalTime parseTime(String timeStr) throws JacksonException {
        try {
            return LocalTime.parse(timeStr);
        } catch (Exception e) {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TIME_FORMAT);
        }
    }
}
