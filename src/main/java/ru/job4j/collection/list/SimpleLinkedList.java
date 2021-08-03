package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size;
    private int modCount;


    private static class Node<E> {
        private final E currentElement;
        private Node<E> nextElement;
        private Node<E> prevElement;

        public Node(E currentElement, Node<E> nextElement, Node<E> prevElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.prevElement = prevElement;
        }

        public Node<E> getNext() {
            return nextElement;
        }

        public void setNext(Node<E> eNode) {
            this.nextElement = eNode;
        }
    }

    /**
     * если голова списка пустая то добавляем єлемент в голову
     * если нет то в темп ставим некст элемент от головы пока не будет пустой некст
     * тогда в некст и цепляем нашего
     */
    @Override
    public void add(E value) {
        if (head == null) {
            head = new Node<>(value, null, null);
        } else {
            Node<E> temp = head;
            while (temp.getNext() != null) {
                temp = head.getNext();
            }
            temp.setNext(new Node<>(value, null, temp));
        }
        size++;
        modCount++;
    }

    /**
     * двигаемся по нексту от головы столько раз == индекс
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.nextElement;
        }
        return temp.currentElement;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpledLinkedListIterator();
    }

    /**
     * стартуем из головы Node<E> node = head
     * также на момент создания фиксируем this.currenModCount = modCount
     */
    private class SimpledLinkedListIterator implements Iterator<E> {
        private Node<E> node = head;
        private int currenModCount;

        public SimpledLinkedListIterator() {
            this.currenModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        /**
         * отдаем значение текущего нода и ставим в нода следующего
         * E value = node.currentElement;
         * node = node.nextElement;
         */
        @Override
        public E next() {
            if (currenModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = node.currentElement;
            node = node.nextElement;
            return value;
        }
    }

    public int getSize() {
        return size;
    }
}