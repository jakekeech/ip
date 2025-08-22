import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

        List<String> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
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
                tasks.add(input);
                System.out.println(line);
                System.out.println("Added: " + input);
                System.out.println(line);
            }
        }

        sc.close();
    }
}