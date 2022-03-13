package ru.job4j.function;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = map::put;
        biCon.accept(1, "one");
        biCon.accept(2, "two");
        biCon.accept(3, "three");
        biCon.accept(4, "four");
        biCon.accept(5, "five");
        biCon.accept(6, "six");
        biCon.accept(7, "seven");

        BiPredicate<Integer, String> biPred = (key, value) -> key % 2 == 0 || value.length() == 4;

        for (Integer i : map.keySet()) {
            if (biPred.test(i, map.get(i))) {
                System.out.printf("key: %d value: %s%n", i, map.get(i));
            }
        }
        /* new ArrayList<>(map.values()) */
        Supplier<List<String>> sup = () -> map.values().stream().collect(Collectors.toList());

        Consumer<String> con = System.out::println;

        Function<String, String> func = s -> s.toUpperCase(Locale.ROOT);

        sup.get().forEach(s -> con.accept(func.apply(s)));
    }
}