import java.util.*;

public class Dume {
    private static final String line = "____________________________________________________________";

    private static void say(String text) {
        System.out.println(line);
        System.out.println(text);
        System.out.println(line);
    }

    private static void checkNonEmpty(String string, String thing) {
        if (string == null || string.isBlank()) {
            throw new DumeException("The description of " + thing + " cannot be empty!");
        }
    }

    private static String[] split(String text, String delim, String err) {
        String[] parts = text.split(delim, 2);
        if (parts.length < 2) throw new DumeException(err);
        return parts;
    }

    private static int parseIndex(String arg, int size) {
        int id;

        try {
            id = Integer.parseInt(arg.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DumeException("Please give a task number!");
        }

        if (id < 0 || id >= size) {
            throw new DumeException("That task does not exist!");
        }

        return id;
    }

    public static void main(String[] args) {
        Storage storage = new Storage("data/dume.txt");
        List<Task> tasks = storage.load();

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

        Scanner sc = new Scanner(System.in);

        while (true) {
            String raw = sc.nextLine();
            String cmd = raw.trim();
            String lc = cmd.toLowerCase();

            try {
                if (lc.equals("bye")) {
                    storage.save(tasks);
                    say("Bye. Hope to see you again soon!");
                    break;
                } else if (lc.equals("mark")) {
                    throw new DumeException("Please give a task number after 'mark'!");
                } else if (lc.startsWith("mark ")) {
                    int id = parseIndex(cmd.substring(5), tasks.size());
                    tasks.get(id).mark();
                    storage.save(tasks);
                    say("Nice! I've marked this task as done:\n  " + tasks.get(id));
                } else if (lc.equals("unmark")) {
                    throw new DumeException("Please give a task number after 'unmark'!");
                } else if (lc.startsWith("unmark ")) {
                    int id = parseIndex(cmd.substring(7), tasks.size());
                    tasks.get(id).unmark();
                    storage.save(tasks);
                    say("OK, I've marked this task as not done yet:\n  " + tasks.get(id));
                }
                else if (lc.equals("list")) {
                    System.out.println(line);
                    if (!tasks.isEmpty()) {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    } else {
                        say("No tasks yet...");
                    }
                    System.out.println(line);
                } else if (lc.startsWith("todo")) {
                    String details = (cmd.length() > 4) ? cmd.substring(4).trim() : "";
                    checkNonEmpty(details, "a todo");
                    Task t = new Todo(details);
                    tasks.add(t);
                    storage.save(tasks);
                    say("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else if (lc.startsWith("deadline")) {
                    String rest = (cmd.length() > 8) ? cmd.substring(8).trim() : "";
                    checkNonEmpty(rest, "a deadline");
                    String[] p = split(rest, "/by", "A deadline needs a /by time!");
                    String details = p[0].trim();
                    String by = p[1].trim();
                    checkNonEmpty(details, "a deadline");
                    checkNonEmpty(by, "the /by time");
                    Task t = new Deadline(details, by);
                    tasks.add(t);
                    storage.save(tasks);
                    say("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else if (lc.startsWith("event")) {
                    String rest = (cmd.length() > 5) ? cmd.substring(5).trim() : "";
                    checkNonEmpty(rest, "an event");
                    String[] p1 = split(rest, "/from", "An event needs /from and /to!");
                    String details = p1[0].trim();
                    String[] p2 = split(p1[1], "/to", "Missing /to for event!");
                    String from = p2[0].trim();
                    String to = p2[1].trim();
                    checkNonEmpty(details, "an event");
                    checkNonEmpty(from, "the /from time");
                    checkNonEmpty(to, "the /to time");
                    Task t = new Event(details, from, to);
                    tasks.add(t);
                    storage.save(tasks);
                    say("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");

                } else if (lc.equals("delete")) {
                    throw new DumeException("Please give a task number after 'delete'!");
                } else if (lc.startsWith("delete ")) {
                    int id = parseIndex(cmd.substring(7), tasks.size());
                    Task removed = tasks.remove(id);
                    storage.save(tasks);
                    say("Noted. I've removed this task:\n  " + removed + "\nNow you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.");
                } else {
                    throw new DumeException("Sorry! I don't understand!");
                }
            } catch (DumeException e) {
                say(e.getMessage());
            }
        }

        sc.close();
    }
}