package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int i = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return i < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[i++];
    }
}