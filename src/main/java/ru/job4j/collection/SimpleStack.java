package ru.job4j.collection;

import ru.job4j.collection.list.ForwardLinked;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод pop() - возвращать значение и удалять его из коллекции.
     */
    public T pop() {
        return linked.deleteLast();
    }

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     */
    public void push(T value) {
        linked.add(value);
    }

    public String view() {
       return linked.view();
    }

    public int getSize() {
        return linked.getSize();
    }
}