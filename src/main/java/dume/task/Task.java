package dume.task;

public abstract class Task {
    protected final String details;
    protected boolean done;

    public Task(String details) {
        this.details = details;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String statusIcon() {
        return done ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + statusIcon() + "] " + details;
    }

    public abstract String toFileFormat();

}
