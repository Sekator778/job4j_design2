package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 1, 22, 1));
        ListUtils.removeIf(input, i -> (i == 1));
        assertThat(Arrays.asList(0, 2, 22), Is.is(input));
    }

    @Test
    public void replaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.replaceIf(input, i -> ((i + 1) == 2), 777);
        assertThat(Arrays.asList(0, 777, 2), Is.is(input));
    }

    @Test
    public void removeAll() {
        List<Integer> listFirst = new ArrayList<>();
        listFirst.add(1);
        listFirst.add(1);
        listFirst.add(2);
        List<Integer> listSecond = new ArrayList<>();
        listSecond.add(4);
        listSecond.add(1);
        ListUtils.removeAll(listFirst, listSecond);
        assertThat(Collections.singletonList(2), Is.is(listFirst));
    }

    @Test
    public void removeAllTwice() {
        List<String> listFirst = new ArrayList<>();
        listFirst.add("Black");
        listFirst.add("White");
        listFirst.add("Red");
        listFirst.add("Red");
        List<String> listSecond = new ArrayList<>();
        listSecond.add("White");
        listSecond.add("Red");
        ListUtils.removeAll(listFirst, listSecond);
        assertThat(Collections.singletonList("Black"), Is.is(listFirst));
    }
}