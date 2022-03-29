package ru.job4j.ex;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(5);

        list.add(1);
        list.add(null);
        list.add(2);


        list.forEach(System.out::println);
        LinkedList linkedList = new LinkedList();
        linkedList.add(2);
    }
}
