package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;

public class Exit implements UserAction {


    @Override
    public String name() {
        return "=== Choose for EXIT ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        return false;
    }
}
