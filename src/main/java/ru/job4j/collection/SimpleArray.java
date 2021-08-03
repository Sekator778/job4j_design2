package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int modCount = 0;

    /**
     * конструктор по дефолту создает внутренний размер
     */
    public SimpleArray() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * тут размер мы определяем на этапе создания
     *
     * @param initialCapacity - размер внутреннего массива
     */
    public SimpleArray(int initialCapacity) {
        if (initialCapacity > 0) {
            this.container = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.container = new Object[DEFAULT_CAPACITY];
        }
    }

    /**
     * получение элемента по индексу
     *
     * @param index индекс
     * @return элемент приведен к типу Т
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        return (T) container[index];
    }

    /**
     * добавляем элемент
     * первым делом смотрим или есть место
     * если надо т о увеличиваем внутренний массив
     *
     * @param model то что добавляем в массив
     */
    public void add(T model) {
        ensureCapacity(size + 1);
        container[size++] = model;
        modCount++;
    }

    /**
     * насколько увеличиваем
     *
     * @param capacity число - размер увеличенного массива
     */
    private void ensureCapacity(int capacity) {
        if (capacity > container.length) {
            int newCapacity = container.length == 0 ? 10 : container.length * 2;
            grow(newCapacity);
        }
    }

    /**
     * копируем массив в новый
     *
     * @param newCapacity таким размером
     */
    private void grow(int newCapacity) {
        container = Arrays.copyOf(container, newCapacity);
    }

    /**
     * итератор
     *
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator();
    }

    /**
     * (expectedModCount != modCount). Если условие выполнено,
     * значит на момент итерирования была изменена коллекция, поэтому вылетает исключение
     * внутренний класс который переопределяет поведение методов
     * интерфейса итератор, а именно:
     * есть ли следующий ответ да если текущий индекс меньше размера массива
     * следующий элемент получаем если проходи два условия
     * 1. что не изменялся массив во время ходьбы по нем
     * 2. что есть следующий
     */
    private class SimpleArrayIterator implements Iterator<T> {
        private int index;
        private final int expectedModCount;

        public SimpleArrayIterator() {
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            } else if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[index++];
        }
    }
}