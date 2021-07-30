package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] array;
    private int row = 0;
    private int col = 0;

    public MatrixIt(int[][] data) {
        this.array = data;
    }

    /**
     * метод пропускает пустые ячейки
     * array[row].length == col
     * особенность метода в том что если длина массива 0 соответственно и элементов там ноль
     * тут хорошо видно что количество элементов в массиве равно array.length - 1
     */
    @Override
    public boolean hasNext() {
        while (row < array.length && array[row].length == col) {
            row++;
            col = 0;
        }
        return row < array.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[row][col++];
    }
}