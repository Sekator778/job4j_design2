package ru.job4j.ex.initialization;

import ru.job4j.ex.initialization.Car;

public class Truck extends Car {

    private static int truckCount = 0;

    private int yearOfManufacture;
    private String model;
    private int maxSpeed;

    public Truck(int yearOfManufacture, String model, int maxSpeed) {
        Car.carCounter++;
        System.out.println("this is class Truck: " + carCounter);
        this.yearOfManufacture = yearOfManufacture;
        this.model = model;
        this.maxSpeed = maxSpeed;

        truckCount++;
    }
}