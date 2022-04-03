package ru.job4j.tracker.action;

import ru.job4j.tracker.*;
import ru.job4j.tracker.model.Item;

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
    public boolean execute(Input input, Store memTracker) {
        int id = input.askInt(" Enter id: ");
        Item item = memTracker.findById(id);
        if (item != null) {
            output.println(item);
        } else {
            output.println("Application with entered id: " + id + " not found.");
        }
        return true;
    }
}
