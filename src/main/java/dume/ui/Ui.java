package dume.ui;

import dume.task.Task;

import java.util.List;

/**
 * Handles all user interaction: greetings, messages, errors, and task lists.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";

    /**
     * Prints the welcome message and ASCII logo.
     *
     * @param logo ASCII art logo
     */
    public void welcome(String logo) {
        System.out.println("Hello! I'm DUM-E");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a given message between divider lines.
     *
     * @param text message to print
     */
    public void say(String text) {
        System.out.println(LINE);
        System.out.println(text);
        System.out.println(LINE);
    }

    /**
     * Displays the given list of tasks.
     *
     * @param tasks tasks to display
     */
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

    /**
     * Displays an error message.
     *
     * @param message error text
     */
    public void showError(String message) {
        say(message);
    }
}
