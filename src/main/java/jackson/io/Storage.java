package jackson.io;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import jackson.JacksonException;
import jackson.task.Task;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createDataFile() throws JacksonException {
        File dataFile = new File(filePath);
        File dataDir = dataFile.getParentFile();
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new JacksonException(JacksonException.ErrorType.FILE_CREATE_ERROR, e.getMessage());
            }
        }
    }

    /**
     * Load tasks from the data file.
     * 
     * @return List of lines from the data file.
     * @throws JacksonException
     */
    public List<String> load() throws JacksonException {
        // create data file if it does not exist
        createDataFile();
        File dataFile = new File(filePath);
        List<String> lines = new ArrayList<>();
        try (Scanner s = new Scanner(dataFile)) {
            while (s.hasNext()) {
                lines.add(s.nextLine());
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new JacksonException(JacksonException.ErrorType.FILE_NOT_FOUND, e.getMessage());
        }
    }

    /**
     * Save tasks to the data file.
     * 
     * @param tasks List of tasks to save
     * @throws JacksonException if file writing fails
     */
    public void save(List<Task> tasks) throws JacksonException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            throw new JacksonException(JacksonException.ErrorType.FILE_WRITE_ERROR, e.getMessage());
        }
    }
}
