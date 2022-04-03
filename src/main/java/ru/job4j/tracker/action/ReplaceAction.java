package ru.job4j.tracker.action;

import ru.job4j.tracker.*;
import ru.job4j.tracker.model.Item;

public class ReplaceAction implements UserAction {
    private final Output output;

    public ReplaceAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Edit item ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        int id = input.askInt("Enter id:");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (memTracker.replace(id, item)) {
            output.println("Application changed successfully.");
        } else {
            output.println("Order replacement error.");
        }
        return true;
    }
}
