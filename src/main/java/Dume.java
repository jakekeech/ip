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
            String input = sc.nextLine().trim().toLowerCase();

            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.startsWith("mark ")) {
                int id = Integer.parseInt(input.substring(5).trim()) - 1;
                Task task = tasks.get(id);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + task);
                System.out.println(line);
            } else if (input.startsWith("unmark ")) {
                int id = Integer.parseInt(input.substring(7).trim()) - 1;
                Task task = tasks.get(id);
                task.unmark();
                System.out.println(line);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + task);
                System.out.println(line);
            } else if (input.equals("list")) {
                System.out.println(line);
                if (!tasks.isEmpty()) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else {
                    System.out.println("No tasks yet...");
                }
                System.out.println(line);
            } else if (input.startsWith("todo ")) {
                String details = input.substring(5).trim();
                Task task = new Todo(details);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split("/by", 2);
                String details = parts[0].trim();
                String by = parts[1].trim();
                Task task = new Deadline(details, by);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            } else if (input.toLowerCase().startsWith("event ")) {
                String[] parts = input.substring(6).split("/from", 2);
                String details = parts[0].trim();
                String[] period = parts[1].split("/to", 2);
                String from = period[0].trim();
                String to = period[1].trim();
                Task task = new Event(details, from, to);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + task);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            }
            else {
                Task task = new Todo(input);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Added: " + input);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(line);
            }
        }

        sc.close();
    }
}