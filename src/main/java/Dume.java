import java.util.*;

public class Dume {
    public static void main(String[] args) {
        String logo = """
     ______   __   __  __   __         _______
    |      | |  | |  ||  |_|  |       |       |
    |  _    ||  | |  ||       | ____  |    ___|
    | | |   ||  |_|  ||       ||____| |   |___
    | |_|   ||       ||       |       |    ___|
    |       ||       || ||_|| |       |   |___
    |______| |_______||_|   |_|       |_______|
    """;
        String line = "____________________________________________________________";

        System.out.println("Hello! I'm DUM-E");
        System.out.println(logo);
        System.out.println("What can I do for you?");

        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.toLowerCase().startsWith("mark ")) {
                int id = Integer.parseInt(input.substring(5).trim()) - 1;
                Task task = tasks.get(id);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + task);
                System.out.println(line);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                int id = Integer.parseInt(input.substring(7).trim()) - 1;
                Task task = tasks.get(id);
                task.unmark();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + task);
                System.out.println(line);
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                if (!tasks.isEmpty()) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else {
                    System.out.println("No tasks yet...");
                }
                System.out.println(line);
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Added: " + input);
                System.out.println(line);
            }
        }

        sc.close();
    }
}