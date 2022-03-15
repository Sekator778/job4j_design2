package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaUsage {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("eeeee", "a", "ccc", "dddd", "bb");

        Comparator<String> comparator = (left, right) -> {
            System.out.printf("compare-> stringLeft: %s, length: %d with stringRight: %s, length: %d%n",
                              left, left.length(),  right, right.length());
            return Integer.compare(right.length(), left.length());
        };

        strings.sort(comparator);
        strings.forEach(System.out::println);
    }
}