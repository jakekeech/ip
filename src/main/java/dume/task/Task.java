package dume.task;

/**
 * Represents a generic task with a description and completion status.
 */
public abstract class Task {
    protected final String details;
    protected boolean done;

    /**
     * Constructs a new task.
     *
     * @param details the description of the task
     */
    public Task(String details) {
        this.details = details;
        this.done = false;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns a string icon showing completion status.
     *
     * @return "X" if task is completed, otherwise " " (space)
     */
    public String getStatusIcon() {
        return done ? "X" : " ";
    }

    /**
     * Checks if this task is completed.
     *
     * @return true if task is marked as done, false otherwise
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Checks if this task's details contain the given query.
     * The match is case-insensitive.
     *
     * @param query the keyword to search for
     * @return true if details contain the query, false otherwise
     */
    public boolean matches(String query) {
        if (query == null) return false;
        return details.toLowerCase().contains(query.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + details;
    }

    /**
     * Returns a string suitable for saving to storage.
     *
     * @return save format string
     */
    public abstract String toFileFormat();

}
