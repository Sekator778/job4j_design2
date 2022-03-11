package ru.job4j.tracker;

public class Main {
    public static void main(String[] args) {
        SingleTracker one = SingleTracker.getInstance();
        System.out.println(one);
        SingleTracker two = SingleTracker.getInstance();
        System.out.println(two);
    }
}
