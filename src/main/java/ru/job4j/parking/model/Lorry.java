package ru.job4j.parking.model;

public class Lorry implements Vehicle {
    public int size;

    public Lorry(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "i'm big lorry";
    }
}
