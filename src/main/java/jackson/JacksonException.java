package jackson;

public class JacksonException extends Exception {
    public enum ErrorType {
        UNKNOWN_COMMAND,
        EMPTY_TASK_DESCRIPTION,
        INVIALID_TASK_FORMAT,
        INVALID_TASK_INDEX,
        EMPTY_TASK_INDEX,
        MISSING_TASK_INDEX,
        INVALID_TASK_FILE_FORMAT,
        FILE_NOT_FOUND,
        FILE_WRITE_ERROR,
        FILE_CREATE_ERROR
    }

    private final static String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means.";
    private final static String EMPTY_TASK_MESSAGE = "The description of a task cannot be empty. \nThe format is as follows: \n";
    private final static String INVALID_TASK_FORMAT_MESSAGE = "The format of the task is invalid. \nThe format is as follows: \n";
    private final static String UNKNOWN_ERROR_MESSAGE = "An unknown error occurred.";
    private final static String INVALID_TASK_INDEX_MESSAGE = "Invalid task number.";
    private final static String EMPTY_TASK_INDEX_MESSAGE = "The task index provided is empty.";
    private final static String FILE_CREATE_ERROR_MESSAGE = "An error occurred while creating the file.";
    private final static String INVALID_TASK_FILE_FORMAT_MESSAGE = "The task in the file has an invalid format.";
    private final static String FILE_NOT_FOUND_MESSAGE = "The specified file was not found.";
    private final static String FILE_WRITE_ERROR_MESSAGE = "An error occurred while writing to the file.";

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
        switch(errorType) {
        case UNKNOWN_COMMAND:
            return UNKNOWN_COMMAND_MESSAGE;
        case EMPTY_TASK_DESCRIPTION:
            return EMPTY_TASK_MESSAGE + message;
        case INVIALID_TASK_FORMAT:
            return INVALID_TASK_FORMAT_MESSAGE + message;
        case INVALID_TASK_INDEX:
            return INVALID_TASK_INDEX_MESSAGE;
        case EMPTY_TASK_INDEX:
            return EMPTY_TASK_INDEX_MESSAGE;
        case FILE_CREATE_ERROR:
            return FILE_CREATE_ERROR_MESSAGE + message;
        case INVALID_TASK_FILE_FORMAT:
            return INVALID_TASK_FILE_FORMAT_MESSAGE;
        case FILE_NOT_FOUND:
            return FILE_NOT_FOUND_MESSAGE + (message != null ? (": " + message) : "");
        case FILE_WRITE_ERROR:
            return FILE_WRITE_ERROR_MESSAGE + (message != null ? (": " + message) : "");
        default:
            return UNKNOWN_ERROR_MESSAGE;
        }
    }
}
