package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestMaxMin {
    @Test
    public void whenFindMaxInt() {
        List<Integer> list = Arrays.asList(1, 10, 2, 20);
        MaxMin maxMin = new MaxMin();
        Integer max = maxMin.max(list, new CompareInt());
        assertThat(max, is(20));
    }

    @Test
    public void whenFindMaxStringLength() {
        List<String> list = Arrays.asList("o", "on", "one");
        MaxMin maxMin = new MaxMin();
        String max = maxMin.max(list, new CompareStr());
        assertThat(max, is("one"));
    }

    @Test
    public void whenFindMinInt() {
        List<Integer> list = Arrays.asList(1, 10, -2, 20);
        MaxMin maxMin = new MaxMin();
        Integer min = maxMin.min(list, new CompareInt());
        assertThat(min, is(-2));
    }

    @Test
    public void whenFindMinStringLength() {
        List<String> list = Arrays.asList("on", "o", "one");
        MaxMin maxMin = new MaxMin();
        String min = maxMin.min(list, new CompareStr());
        assertThat(min, is("o"));
    }

}
