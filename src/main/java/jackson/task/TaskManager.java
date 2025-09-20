package jackson.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jackson.JacksonException;
import jackson.task.Task.TaskType;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public TaskManager(List<String> lines) throws JacksonException {
        for (String line : lines) {
            Parser.parseTask(line, this);
        }
    }

    /**
     * Find tasks that contain the given keyword.
     * 
     * @param keyword The keyword to search for in task descriptions
     * @return List of tasks that contain the given keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(java.util.stream.Collectors.toList());
        return matchingTasks;
    }

    /**
     * Get the task at the given index.
     * 
     * @param index
     * @return The task at the given index, or null if the index is invalid.
     * @throws JacksonException
     */
    public Task get(int index) throws JacksonException {
        if (isValidIndex(index)) {
            return tasks.get(index - 1);
        }
        return null;
    }

    /**
     * Get all tasks.
     * 
     * @return List of all tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Get tasks filtered by type and date/time.
     * 
     * @param type     The type of task to filter by.
     * @param isBefore If true, get tasks before the given date/time. If false, get
     *                 tasks after the given date/time.
     * @param date     The date to filter by.
     * @param time     The time to filter by.
     * @return List of tasks that match the given criteria.
     */
    public List<Task> getFilteredTasks(
            TaskType type, boolean isBefore, LocalDate date, LocalTime time) {
        List<Task> filteredTasks = tasks.stream()
                .filter(t -> t.getType() == type)
                .filter(t -> t.isInRange(isBefore, date, time))
                .collect(java.util.stream.Collectors.toList());
        return filteredTasks;
    }

    /**
     * Add a task to the task list.
     * 
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark or unmark a task as done.
     * 
     * @param task
     * @param isDone true to mark as done, false to unmark.
     */
    public void markTask(Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmark();
        }
    }

    /**
     * Delete a task from the task list.
     * 
     * @param task
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Check if the given index is valid.
     * 
     * @param index
     * @return true if the index is valid, false otherwise.
     * @throws JacksonException
     */
    public boolean isValidIndex(int index) throws JacksonException {
        if (index > 0 && index <= tasks.size()) {
            return true;
        } else {
            throw new JacksonException(JacksonException.ErrorType.INVALID_TASK_INDEX);
        }
    }
}
