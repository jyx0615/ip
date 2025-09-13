package jackson;

import java.io.IOException;

public class JacksonException extends Exception {
    public enum ErrorType {
        UNKNOWN_COMMAND,
        EMPTY_TASK_DESCRIPTION,
        INVIALID_TASK_FORMAT,
        TOO_MANY_TASKS,
        INVALID_TASK_INDEX,
        MISSING_TASK_INDEX,
        IO_ERROR,
        INVALID_TASK_FILE_FORMAT,
        FILE_NOT_FOUND
    }

    private final static String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means.";
    private final static String EMPTY_TASK_MESSAGE = "The description of a task cannot be empty.";
    private final static String INVALID_TASK_FORMAT_MESSAGE = "The format of the task is invalid. \nThe format is as follows: \n";
    private final static String UNKNOWN_ERROR_MESSAGE = "An unknown error occurred.";
    private final static String TOO_MANY_TASKS = "You have too many tasks in your list.";
    private final static String INVALID_TASK_INDEX = "The task index provided is invalid.";
    private final static String IO_ERROR_MESSAGE = "An I/O error occurred: ";
    private final static String INVALID_TASK_FILE_FORMAT_MESSAGE = "The task in the file has an invalid format.";
    private final static String FILE_NOT_FOUND_MESSAGE = "The specified file was not found.";

    private String message;
    private final ErrorType errorType;

    public JacksonException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public JacksonException(ErrorType errorType, String message) {
        this(errorType);
        this.message = message;
    }

    public JacksonException(IOException e) {
        super(e);
        this.errorType = ErrorType.IO_ERROR;
        this.message = e.getMessage();
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
            return EMPTY_TASK_MESSAGE;
        case INVIALID_TASK_FORMAT:
            return INVALID_TASK_FORMAT_MESSAGE + message;
        case TOO_MANY_TASKS:
            return TOO_MANY_TASKS;
        case INVALID_TASK_INDEX:
            return INVALID_TASK_INDEX;
        case IO_ERROR:
            return IO_ERROR_MESSAGE + message;
        case INVALID_TASK_FILE_FORMAT:
            return INVALID_TASK_FILE_FORMAT_MESSAGE;
        case FILE_NOT_FOUND:
            return FILE_NOT_FOUND_MESSAGE + (message != null ? (": " + message) : "");
        default:
            return UNKNOWN_ERROR_MESSAGE;
        }
    }
}
