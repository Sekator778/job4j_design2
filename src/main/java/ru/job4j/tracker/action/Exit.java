package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

public class Exit implements UserAction {


    @Override
    public String name() {
        return "=== Choose for EXIT ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
