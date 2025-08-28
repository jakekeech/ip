import java.io.*;
import java.util.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parse(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return tasks;
    }

    public void save(List<Task> tasks) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    writer.write(task.toFileFormat());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private Task parse(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean done = parts[1].equals("1");
        String details = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(details);
                if (done) {
                    todo.mark();
                }
                return todo;
            case "D":
                Deadline deadline = new Deadline(details, parts[3]);
                if (done) {
                    deadline.mark();
                }
                return deadline;
            case "E":
                String[] times = parts[3].split(" to ", 2);
                String start = times.length > 0 ? times[0] : "";
                String end = times.length > 1 ? times[1] : "";
                Event event = new Event(details, start, end);
                if (done) {
                    event.mark();
                }
                return event;
            default:
                return null;
        }
    }
}
