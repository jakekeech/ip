package dume.task;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around a list of tasks, providing higher-level operations
 * such as add, remove, and retrieval by index.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList initialized with existing tasks.
     *
     * @param initial initial list of tasks
     */
    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    /**
     * Returns the internal list of tasks.
     *
     * @return list of tasks
     */
    public List<Task> asList() {
        return tasks;
    }

    /**
     * Returns the number of tasks.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param id zero-based index
     * @return task at that index
     */
    public Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Adds a task.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index.
     *
     * @param id zero-based index
     * @return removed task
     */
    public Task remove(int id) {
        return tasks.remove(id);
    }
}
