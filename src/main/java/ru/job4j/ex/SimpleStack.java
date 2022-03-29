package ru.job4j.ex;

import ru.job4j.collection.list.ForwardLinked;

public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }
}