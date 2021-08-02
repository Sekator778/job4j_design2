package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.container = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.container = new Object[DEFAULT_CAPACITY];
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        return (T) container[index];
    }

    public void add(T model) {
        ensureCapacity(size + 1);
        container[size++] = model;
        modCount++;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > container.length) {
            int newCapacity = container.length == 0 ? 10 : container.length * 2;
            grow(newCapacity);
        }
    }

    private void grow(int newCapacity) {
        container = Arrays.copyOf(container, newCapacity);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}