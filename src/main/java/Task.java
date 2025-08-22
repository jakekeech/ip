public class Task {
    private final String details;
    private boolean done;

    public Task(String details) {
        this.details = details;
        this.done = false;
    }

    public void complete() {
        this.done = true;
    }

    public String statusIcon() {
        return done ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + statusIcon() + "]" + details;
    }

}
