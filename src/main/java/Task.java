public class Task {
    private final String details;
    private boolean done;

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

}
