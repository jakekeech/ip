public class Todo extends Task {
    public Todo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (done ? "1" : "0") + " | " + details;
    }
}
