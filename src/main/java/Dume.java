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

        Ui ui = new Ui();
        Storage storage = new Storage("data/dume.txt");
        TaskList tasks = new TaskList(storage.load());

        ui.welcome(logo);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit && sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                exit = Parser.process(input, tasks, ui, storage);
            } catch (DumeException e) {
                ui.showError(e.getMessage());
            }
        }
        sc.close();
    }
}