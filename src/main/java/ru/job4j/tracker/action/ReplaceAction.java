package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Output;
import ru.job4j.tracker.Tracker;

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
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter id:");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            output.println("Application changed successfully.");
        } else {
            output.println("Order replacement error.");
        }
        return true;
    }
}
