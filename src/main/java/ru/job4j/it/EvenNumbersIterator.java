package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int i;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (i > data.length - 1) {
            throw new NoSuchElementException();
        }
        return haveEven();
    }

    @Override
    public Integer next() {
        hasNext();
        return data[i++];
    }

    /**
     * есть ли впереди элементы четные ?
     * и двигаем курсор (i) к четному элементу
      */
    private boolean haveEven() {
        while (i <= data.length - 1) {
            if (data[i] % 2 == 0) {
                return true;
            }
            i++;
        }
        return false;
    }
}
