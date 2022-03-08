package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Output;
import ru.job4j.tracker.Tracker;

public class FindByIdItem implements UserAction {
    private final Output output;

    public FindByIdItem(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find item by id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt(" Enter id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            output.println(item);
        } else {
            output.println("Application with entered id: " + id + " not found.");
        }
        return true;
    }
}
