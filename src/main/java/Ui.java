import java.util.List;

public class Ui {
    private static final String LINE = "____________________________________________________________";

    public void welcome(String logo) {
        System.out.println("Hello! I'm DUM-E");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void say(String text) {
        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);
    }

    public void showList(List<Task> tasks) {
        System.out.println(LINE);
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet...");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    public void showError(String message) {
        say(message);
    }
}
