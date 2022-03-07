package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        /* Находим индекс */
        int index = indexOf(id);
        /* Если индекс найден возвращаем item, иначе null */
        return index != -1 ? items[index] : null;
    }

    /* получение списка по имени -*/
    public Item[] findByName(String key) {
        Item[] rsl = new Item[100];
        int k = 0;
        for (Item i : items) {
            if (i == null) {
                break;
            }
            if (i.getName().equals(key)) {
                rsl[k++] = i;
            }
        }
        return Arrays.copyOf(rsl, k);
    }


    /* получение списка всех заявок - */
    public Item[] findAll() {
        return items;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }


    public boolean replace(int id, Item bugWithDesc) {
        /* Находим индекс */
        int index = indexOf(id);
        if (index == -1) {
            return false;
        } else {
            bugWithDesc.setId(items[index].getId());
            items[index] = bugWithDesc;
            return true;
        }
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        System.arraycopy(items, index + 1, items, index, (ids - index));
        items[ids] = null;
        size--;
        return true;
    }
}