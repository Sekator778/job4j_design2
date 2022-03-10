package ru.job4j.ex.initialization;

public class Car {

    public static int carCounter = 10;

    private String description = "Начальное значение поля description";

    public Car() {
        System.out.println(description);
        description = "Абстрактная машина";
        System.out.println(description);
    }

    public String getDescription() {
        return description;
    }
}