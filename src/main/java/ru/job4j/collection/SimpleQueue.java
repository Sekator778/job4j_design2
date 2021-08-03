package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Метод poll() - должен возвращать первое значение и удалять его из коллекции.
     */
    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * Метод push(T value) - помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
    }

    public String viewIn() {
        return in.view();
    }
    public String viewOut() {
        return out.view();
    }
}