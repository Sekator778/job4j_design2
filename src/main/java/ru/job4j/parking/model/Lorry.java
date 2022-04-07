package ru.job4j.parking.model;

public class Lorry implements Vehicle {
    public int size;
    private final int id;

    public Lorry(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "i'm big lorry my id: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lorry lorry = (Lorry) o;
        if (size != lorry.size) {
            return false;
        }
        return id == lorry.id;
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + id;
        return result;
    }
}
