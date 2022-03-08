package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                String name = input.askStr("=== Create a new Item ===" + System.lineSeparator() + " Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
                System.out.println("Added application: " + item);
            } else if (select == 1) {
                System.out.println("=== Show all items ===");
                Item[] items = tracker.findAll();
                if (items.length > 0) {
                    for (Item item : items) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("The repository does not yet contain claims.");
                }
            } else if (select == 2) {
                int id = input.askInt("=== Edit item === \n Enter id:");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                if (tracker.replace(id, item)) {
                    System.out.println("Application changed successfully.");
                } else {
                    System.out.println("Order replacement error.");
                }
            } else if (select == 3) {
                int id = input.askInt("=== Delete item === " + System.lineSeparator() + " Enter id: ");
                if (tracker.delete(id)) {
                    System.out.println("Request deleted successfully.");
                } else {
                    System.out.println("Application deletion error.");
                }
            } else if (select == 4) {
                int id = input.askInt("=== Find item by id ===" + System.lineSeparator() + " Enter id: ");
                Item item = tracker.findById(id);
                if (item != null) {
                    System.out.println(item);
                } else {
                    System.out.println("Application with entered id: " + id + " not found.");
                }
            } else if (select == 5) {
                String name = input.askStr("=== Find items by name ===" + System.lineSeparator() + " Enter name: ");
                Item[] items = tracker.findByName(name);
                if (items.length > 0) {
                    for (Item item : items) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("Application with: " + name + " not found.");
                }
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        String[] menu = {
                "Add new Item", "Show all items", "Edit item",
                "Delete item", "Find item by id", "Find items by name",
                "Exit Program"
        };
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}