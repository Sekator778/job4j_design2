package ru.job4j.tracker.action;

import ru.job4j.tracker.*;
import ru.job4j.tracker.model.Item;

import java.util.List;

public class FindItemByName implements UserAction {
    private final Output output;

    public FindItemByName(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String name = input.askStr(" Enter name: ");
        List<Item> itemList = memTracker.findByName(name);
        if (itemList.isEmpty()) {
            output.println("Application with: " + name + " not found.");
            return false;
        } else {
            itemList.forEach(output::println);
            return true;
        }
    }
}
