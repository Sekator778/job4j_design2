package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Output;
import ru.job4j.tracker.Tracker;

public class DeleteAction implements UserAction {
    private final Output output;

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Delete item === ";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt(" Enter id: ");
        if (tracker.delete(id)) {
            output.println("Request deleted successfully.");
        } else {
           output.println("Application deletion error.");
        }
        return true;
    }
}
