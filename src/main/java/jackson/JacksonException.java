package jackson;
public class JacksonException extends Exception {
    public enum ErrorType {
        UNKNOWN_COMMAND,
        EMPTY_TASK_DESCRIPTION,
        INVIALID_TASK_FORMAT,
        INVALID_TASK_INDEX,
        EMPTY_TASK_INDEX,
    }

    private final static String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means.";
    private final static String EMPTY_TASK_MESSAGE = "The description of a task cannot be empty.";
    private final static String INVALID_TASK_FORMAT_MESSAGE = "The format of the task is invalid. \nThe format is as follows: \n";
    private final static String UNKNOWN_ERROR_MESSAGE = "An unknown error occurred.";
    private final static String INVALID_TASK_INDEX_MESSAGE = "The task index provided is invalid.";
    private final static String EMPTY_TASK_INDEX_MESSAGE = "The task index provided is empty.";

    private String message;
    private final ErrorType errorType;

    public JacksonException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public JacksonException(ErrorType errorType, String message) {
        this(errorType);
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
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
        default:
            return UNKNOWN_ERROR_MESSAGE;
        }
    }
}
