package ru.job4j.iterator;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
    }

    /**
     * два итератора один по data другой внутри массива
     * !cursor.hasNext() - тут если закончился итератор во внутреннем массиве то
     * cursor = data.next(); итератор стает итератор из другого массива
     * ну при условии что есть следующий массив data.hasNext()
     */
    @Override
    public boolean hasNext() {
        while (data.hasNext() && !cursor.hasNext()) {
            cursor = data.next();
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}