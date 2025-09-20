package jackson;

public class JacksonException extends Exception {
    public enum ErrorType {
        UNKNOWN_COMMAND,
        INVALID_COMMAND_FORMAT,
        INVALID_TASK_INDEX,
        EMPTY_TASK_INDEX,
        MISSING_TASK_INDEX,
        INVALID_TASK_FILE_FORMAT,
        INVALID_DATE_FORMAT,
        INVALID_TIME_FORMAT,
        FILE_NOT_FOUND,
        FILE_WRITE_ERROR,
        FILE_CREATE_ERROR,
        INVALID_EVENT_TIME
    }

    private static final String UNKNOWN_COMMAND_MESSAGE = 
        "I'm sorry, but I don't know what that means.";
    private static final String INVALID_COMMAND_FORMAT_MESSAGE = 
        "The format of the command is invalid. \nThe format is as follows: \n";
    private static final String UNKNOWN_ERROR_MESSAGE = 
        "An unknown error occurred.";
    private static final String INVALID_TASK_INDEX_MESSAGE = 
        "Invalid task number.";
    private static final String EMPTY_TASK_INDEX_MESSAGE = 
        "The task index provided is empty.";
    private static final String FILE_CREATE_ERROR_MESSAGE = 
        "An error occurred while creating the file.";
    private static final String INVALID_TASK_FILE_FORMAT_MESSAGE = 
        "The task in the file has an invalid format.";
    private static final String FILE_NOT_FOUND_MESSAGE = 
        "The specified file was not found.";
    private static final String FILE_WRITE_ERROR_MESSAGE = 
        "An error occurred while writing to the file.";
    private static final String INVALID_DATE_FORMAT_MESSAGE = 
        "The date format is invalid. Please use YYYY-MM-DD.";
    private static final String INVALID_TIME_FORMAT_MESSAGE = 
        "The time format is invalid. Please use HH:MM in 24-hour format.";
    private static final String INVALID_EVENT_TIME_MESSAGE = 
        "The event end time cannot be before the start time.";

    private String message;
    private final ErrorType errorType;

    public JacksonException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public JacksonException(ErrorType errorType, String message) {
        this(errorType);
        this.message = message;
    }

    @Override
    public String getMessage() {
        switch (errorType) {
        case UNKNOWN_COMMAND:
            return UNKNOWN_COMMAND_MESSAGE;
        case INVALID_COMMAND_FORMAT:
            return INVALID_COMMAND_FORMAT_MESSAGE + message;
        case INVALID_TASK_INDEX:
            return INVALID_TASK_INDEX_MESSAGE;
        case EMPTY_TASK_INDEX:
            return EMPTY_TASK_INDEX_MESSAGE;
        case FILE_CREATE_ERROR:
            return FILE_CREATE_ERROR_MESSAGE + message;
        case INVALID_TASK_FILE_FORMAT:
            return INVALID_TASK_FILE_FORMAT_MESSAGE;
        case INVALID_DATE_FORMAT:
            return INVALID_DATE_FORMAT_MESSAGE;
        case INVALID_TIME_FORMAT:
            return INVALID_TIME_FORMAT_MESSAGE;
        case INVALID_EVENT_TIME:
            return INVALID_EVENT_TIME_MESSAGE;
        case FILE_NOT_FOUND:
            return FILE_NOT_FOUND_MESSAGE + (message != null ? (": " + message) : "");
        case FILE_WRITE_ERROR:
            return FILE_WRITE_ERROR_MESSAGE + (message != null ? (": " + message) : "");
        default:
            return UNKNOWN_ERROR_MESSAGE;
        }
    }
}
