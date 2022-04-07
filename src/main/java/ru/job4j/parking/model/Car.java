package ru.job4j.parking.model;

public class Car implements Vehicle {
    private final int id;
    public static final int SIZE = 1;


    /**
     * для разделения легковиков от грузовиков + 100_000
     * а так можна ?
     */
    public Car(int id) {
        this.id = id + 100_000;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String toString() {
        return "I'm auto my id: " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
