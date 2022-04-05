package ru.job4j.parking.model;

public class Car implements Vehicle {
    public static final int SIZE = 1;

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "I'm auto";
    }
}
