public class Deadline extends Task{
    private final String by;

    public Deadline(String details, String by) {
        super(details);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeHelper.convert(by) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (done ? "1" : "0") + " | " + details + " | " + by;
    }
}
