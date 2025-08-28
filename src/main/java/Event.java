public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String details, String start, String end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeHelper.convert(start)
                + " to: " + DateTimeHelper.convert(end) + ")";
    }

    @Override
    public String toFileFormat() {
        return "T | " + (done ? "1" : "0") + " | " + details;
    }
}
