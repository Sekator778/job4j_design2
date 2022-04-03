package ru.job4j.tracker.action;

import ru.job4j.tracker.*;
import ru.job4j.tracker.model.Item;

import java.util.List;

public class ShowAllItem implements UserAction {
    private final Output output;

    public ShowAllItem(Output out) {
        this.output = out;
    }

    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        output.println("=== Show all items ===");
        List<Item> itemList = memTracker.findAll();
        if (itemList.isEmpty()) {
            output.println("The repository does not yet contain claims.");
            return false;
        } else {
            itemList.forEach(output::println);
            return true;
        }
    }
}
