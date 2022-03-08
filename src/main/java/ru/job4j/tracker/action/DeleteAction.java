package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item === ";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt(" Enter id: ");
        if (tracker.delete(id)) {
            System.out.println("Request deleted successfully.");
        } else {
            System.out.println("Application deletion error.");
        }
        return true;
    }
}
