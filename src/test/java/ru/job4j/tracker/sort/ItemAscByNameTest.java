package ru.job4j.tracker.sort;

import org.junit.Test;
import ru.job4j.tracker.model.Item;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemAscByNameTest {

    private static final Item I_1 = new Item(1, "z");
    private static final Item I_2 = new Item(2, "s");
    private static final Item I_3 = new Item(3, "a");


    @Test
    public void compare() {
        List<Item> items = Arrays.asList(I_1, I_2, I_3);
        items.sort(new ItemAscByName());
        List<Item> expect = Arrays.asList(I_3, I_2, I_1);
        assertThat(items, is(expect));
    }

    @Test
    public void treeSortTest() {
        Set<Item> items = new TreeSet<>(Collections.reverseOrder());
        items.add(I_3);
        items.add(I_2);
        items.add(I_1);
        items.forEach(System.out::println);
    }
}