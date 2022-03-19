package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-7, 7, -45, 65, 0, 0, -4, 34, 67);
        List<Integer> positive = numbers.stream()
                .filter(number -> number > 0)
                .collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}
