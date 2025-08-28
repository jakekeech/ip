import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    public List<Task> asList() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int id) {
        return tasks.remove(id);
    }
}
