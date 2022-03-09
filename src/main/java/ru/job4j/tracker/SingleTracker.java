package ru.job4j.tracker;

public final class SingleTracker {
    private final Tracker tracker = new Tracker();

    private static final SingleTracker INSTANCE = null;

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (INSTANCE == null) {
            return new SingleTracker();
        } else {
            return INSTANCE;
        }
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public boolean delete(int id) {
        return tracker.delete(id);
    }
}
