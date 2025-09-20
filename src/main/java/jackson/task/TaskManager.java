package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import jackson.JacksonException;
import jackson.task.Task.TaskType;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<String> lines) throws JacksonException {
        Parser.setTaskManager(this);
        for (String line : lines) {
            Parser.parseTask(line, this);
        }
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public Task get(int index) throws JacksonException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        }
        return null;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
    
    public ArrayList<Task> getFilteredTasks(
        TaskType type, boolean isBefore, LocalDate date, LocalTime time
    ) {
        ArrayList<Task> filteredTasks = tasks.stream()
            .filter(t -> t.getType() == type)
            .filter(t -> t.isInRange(isBefore, date, time))
            .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        return filteredTasks;
    }

    public void addTask(Task task) throws JacksonException {
        tasks.add(task);
    }

    public void markTask(Task t, boolean isDone) throws JacksonException {
        if (isDone) {
            t.markAsDone();
        } else {
            t.unmark();
        } 
    }

    public void deleteTask(Task task) throws JacksonException {
        tasks.remove(task);
    }

    public boolean isValidIndex(int index) throws JacksonException {
        if (index > 0 && index <= tasks.size()) {
            return true;
        } else {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }
}
