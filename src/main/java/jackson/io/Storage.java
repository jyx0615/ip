package jackson.io;

import java.util.ArrayList;
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

    public ArrayList<String> load() throws JacksonException {
        // create data file if it does not exist
        createDataFile();
        File dataFile = new File(filePath);
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner s = new Scanner(dataFile)){
            while (s.hasNext()) {
                lines.add(s.nextLine());
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new JacksonException(JacksonException.ErrorType.FILE_NOT_FOUND, e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws JacksonException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    writer.write(tasks.get(i).toFileString() + System.lineSeparator());
                } catch (IOException e) {
                    writer.close();
                    throw new JacksonException(JacksonException.ErrorType.FILE_WRITE_ERROR, e.getMessage());
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new JacksonException(JacksonException.ErrorType.FILE_WRITE_ERROR, e.getMessage());
        }
        
    }
}
