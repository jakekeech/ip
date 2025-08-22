import java.util.Scanner;

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

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }
        }

        sc.close();
    }
}