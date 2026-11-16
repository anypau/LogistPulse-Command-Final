package logistpulse.manager;

import logistpulse.commands.Command;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class CommandManager {
    private final Queue<Command> pending = new ArrayDeque<>();
    private final Queue<Command> postponed = new ArrayDeque<>();
    private final Deque<Command> executed = new ArrayDeque<>();
    private final List<String> log = new ArrayList<>();

    public void enqueue(Command command) {
        if (command == null) throw new IllegalArgumentException("Command required");
        pending.add(command);
        register("QUEUED: " + command);
    }

    public void postpone() {
        Command command = pending.poll();
        if (command == null) {
            register("NOTHING TO POSTPONE");
            return;
        }
        postponed.add(command);
        register("POSTPONED: " + command);
    }

    public void restorePostponed() {
        Command command = postponed.poll();
        if (command == null) {
            register("NOTHING TO RESTORE");
            return;
        }
        pending.add(command);
        register("RESTORED: " + command);
    }

    public void executeNext() {
        Command command = pending.poll();
        if (command == null) {
            register("QUEUE EMPTY");
            return;
        }
        try {
            command.execute();
            executed.push(command);
            register("EXECUTED: " + command);
        } catch (IllegalArgumentException exception) {
            register("FAILED: " + command + " (" + exception.getMessage() + ")");
        }
    }

    public void executeAll() {
        while (!pending.isEmpty()) executeNext();
    }

    public void undoLast() {
        if (executed.isEmpty()) {
            register("NOTHING TO UNDO");
            return;
        }
        Command command = executed.peek();
        try {
            command.undo();
            executed.pop();
            register("UNDONE: " + command);
        } catch (IllegalArgumentException exception) {
            register("UNDO FAILED: " + command + " (" + exception.getMessage() + ")");
        }
    }

    public void register(String action) {
        log.add(action);
        System.out.println(action);
    }

    public List<String> getLog() {
        return List.copyOf(log);
    }

    public void showLog() {
        System.out.println("\n--- COMMAND HISTORY ---");
        log.forEach(System.out::println);
    }
}
