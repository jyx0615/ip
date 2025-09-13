package jackson.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import jackson.JacksonException;
import jackson.file.Parser;

public class TaskManager {
    private static final int MAX_TASKS = 100;
    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    private String dataPath;

    public TaskManager(String dataPath) {
        this.dataPath = dataPath;
        Parser.setTaskManager(this);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public Task addTodoTask(String description) throws JacksonException {
        if (taskCount >= MAX_TASKS) {
            throw new JacksonException(JacksonException.ErrorType.TOO_MANY_TASKS);
        }
        tasks[taskCount ++] = new Todo(description);
        printAddTaskMessage();
        saveTasksToFile();
        return tasks[taskCount - 1];
    }

    public Task addEventTask(String description, String start, String deadline) throws JacksonException{
        if (taskCount >= MAX_TASKS) {
            throw new JacksonException(JacksonException.ErrorType.TOO_MANY_TASKS);
        }
        tasks[taskCount ++] = new Event(description, start, deadline);
        printAddTaskMessage();
        saveTasksToFile();
        return tasks[taskCount - 1];
    }

    public Task addDeadlineTask(String description, String deadline) throws JacksonException {
        if (taskCount >= MAX_TASKS) {
            throw new JacksonException(JacksonException.ErrorType.TOO_MANY_TASKS);
        }
        tasks[taskCount ++] = new Deadline(description, deadline);
        printAddTaskMessage();
        saveTasksToFile();
        return tasks[taskCount - 1];
    }

    public void markTask(int index) throws JacksonException {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("Marked task " + index + " as done.");
            saveTasksToFile();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int index)  throws JacksonException {
        if (index > 0 && index <= taskCount) {
            tasks[index - 1].unmark();
            System.out.println("Unmarked task " + index + ".");
            saveTasksToFile();
        } else {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }

    public boolean isValidIndex(int index) {
        return index > 0 && index <= taskCount;
    }
    
    public void printAddTaskMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1].toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void loadTasksFromFile(String dataPath) throws JacksonException {
        File dataFile = new File(dataPath.toString());
        try (Scanner s = new Scanner(dataFile)){
            while (s.hasNext()) {
                Parser.parseTask(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new JacksonException(JacksonException.ErrorType.FILE_NOT_FOUND, e.getMessage());
        }
    }

    public void saveTasksToFile() throws JacksonException {
        try {
            FileWriter writer = new FileWriter(dataPath, false);
            for (int i = 0; i < taskCount; i++) {
                try {
                    writer.write(tasks[i].toFileString() + System.lineSeparator());
                } catch (IOException e) {
                    writer.close();
                    throw new JacksonException(JacksonException.ErrorType.IO_ERROR, e.getMessage());
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error initializing file writer: " + e.getMessage());
        }
        
    }
}
