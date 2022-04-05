package ru.job4j.parking;

import ru.job4j.parking.model.Vehicle;

public interface Parking {
    boolean addVehicle(Vehicle vehicle);
    boolean removeVehicle(Vehicle vehicle);
}

