package ru.job4j.collection.list;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void getSize() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        assertThat(list.getSize(), Is.is(2));
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void getSize2() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        list.deleteLast();
        assertThat(list.getSize(), Is.is(1));
    }

    @Test
    public void getSize3() {
        ForwardLinked<Integer> list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        list.deleteFirst();
        assertThat(list.getSize(), Is.is(1));
    }
}