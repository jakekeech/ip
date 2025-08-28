package dume.parser;

import dume.DumeException;
import dume.storage.Storage;
import dume.task.Deadline;
import dume.task.Event;
import dume.task.Task;
import dume.task.TaskList;
import dume.task.Todo;
import dume.ui.Ui;

import java.util.Objects;

public class Parser {

    // returns true if user wants to exit
    public static boolean process(String raw, TaskList tasks, Ui ui, Storage storage) {
        String cmd = Objects.requireNonNullElse(raw, "").trim();
        String lc = cmd.toLowerCase();

        if (lc.equals("bye")) {
            storage.save(tasks.asList());
            ui.say("Bye. Hope to see you again soon!");
            return true;
        }

        if (lc.equals("list")) {
            ui.showList(tasks.asList());
            return false;
        }

        if (lc.equals("mark")) throw new DumeException("Please give a task number after 'mark'!");
        if (lc.startsWith("mark ")) {
            int id = parseIndexOrThrow(cmd.substring(5), tasks.size());
            Task task = tasks.get(id);
            task.mark();
            storage.save(tasks.asList());
            ui.say("Nice! I've marked this task as done:\n  " + task);
            return false;
        }

        if (lc.equals("unmark")) throw new DumeException("Please give a task number after 'unmark'!");
        if (lc.startsWith("unmark ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task task = tasks.get(id);
            task.unmark();
            storage.save(tasks.asList());
            ui.say("OK, I've marked this task as not done yet:\n  " + task);
            return false;
        }

        if (lc.equals("delete")) throw new DumeException("Please give a task number after 'delete'!");
        if (lc.startsWith("delete ")) {
            int id = parseIndexOrThrow(cmd.substring(7), tasks.size());
            Task removed = tasks.remove(id);
            storage.save(tasks.asList());
            int n = tasks.size();
            ui.say("Noted. I've removed this task:\n  " + removed
                    + "\nNow you have " + n + " task" + (n == 1 ? "" : "s") + " in the list.");
            return false;
        }

        if (lc.startsWith("todo")) {
            String details = (cmd.length() > 4) ? cmd.substring(4).trim() : "";
            checkNonEmpty(details, "a todo");
            Task task = new Todo(details);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        if (lc.startsWith("deadline")) {
            String rest = (cmd.length() > 8) ? cmd.substring(8).trim() : "";
            checkNonEmpty(rest, "a deadline");
            String[] p = split(rest, "/by", "A deadline needs a /by time!");
            String details = p[0].trim();
            String by = p[1].trim();
            checkNonEmpty(details, "a deadline");
            checkNonEmpty(by, "the /by time");
            Task task = new Deadline(details, by);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        if (lc.startsWith("event")) {
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
            Task task = new Event(details, from, to);
            tasks.add(task);
            storage.save(tasks.asList());
            ui.say("Got it. I've added this task:\n  " + task
                    + "\nNow you have " + tasks.size() + " tasks in the list.");
            return false;
        }

        throw new DumeException("Sorry! I don't understand!");
    }

    // helpers (same logic you had)
    private static String[] split(String text, String delim, String err) {
        String[] parts = text.split(delim, 2);
        if (parts.length < 2) throw new DumeException(err);
        return parts;
    }

    private static void checkNonEmpty(String s, String what) {
        if (s == null || s.isBlank()) {
            throw new DumeException("The description of " + what + " cannot be empty!");
        }
    }

    private static int parseIndexOrThrow(String arg, int size) {
        final int zeroBased;
        try { zeroBased = Integer.parseInt(arg.trim()) - 1; }
        catch (NumberFormatException e) { throw new DumeException("Please give a task number!"); }
        if (zeroBased < 0 || zeroBased >= size) throw new DumeException("That task does not exist!");
        return zeroBased;
    }
}
