package quize.tre;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> eNode = findBy(parent);
        if (eNode.isEmpty()) {
            return false;
        }
        Optional<Node<E>> childNode = findBy(child);
        if (childNode.isPresent()) {
            return false;
        }
        eNode.get().children.add(new Node<>(child));
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = eNode -> eNode.value.equals(value);
        return findByPredicate(predicate);
    }

    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}