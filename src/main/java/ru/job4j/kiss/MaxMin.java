package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.IntPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findMaxMin(value, comparator, x -> x < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findMaxMin(value, comparator, x -> x > 0);
    }

    private <T> T findMaxMin(List<T> value, Comparator<T> comparator, IntPredicate predicate) {
        T result = value.get(0);
        for (int i = 0; i < value.size(); i++) {
            if (predicate.test(comparator.compare(result, value.get(i)))) {
                result = value.get(i);
            }
        }
        return result;
    }
}