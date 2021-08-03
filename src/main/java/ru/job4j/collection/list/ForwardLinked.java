package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    int size;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * удалить первый элемент в списке это означает
     * голову сделать голова.некст
     *
     * @return головы старой значение
     */
    public T deleteFirst() {
        empty();
        Node<T> result = head;
        head = head.next;
        size--;
        return result.value;
    }

    public T deleteLast() {
        empty();
        Node<T> previous = null;
        Node<T> current = head;
        Node<T> next = current.next;
        T value;
        while (next != null) {
            previous = current;
            current = next;
            next = current.next;
        }
        value = current.value;
        if (previous != null) {
            previous.next = null;
        }
        size--;
        return value;
    }

    private void empty() {
        if (head == null) {
            throw new NoSuchElementException("EMPTY");
        }
    }

    public String view() {
        empty();
        int index = 0;
        StringBuilder builder = new StringBuilder();
        Node<T> current = head;
        while (current.next != null) {
            builder.append("index ").append(index++).append(" value ").append(current.value).append(" : ");
            current = current.next;
        }
        builder.append("index ").append(index).append(" value ");
        builder.append(current.value);
        return builder.toString();
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public int getSize() {
        return size;
    }
}