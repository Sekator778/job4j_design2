package ru.job4j.parking;

import ru.job4j.parking.model.Vehicle;

import java.util.Optional;

public interface Parking {
    boolean addVehicle(Vehicle vehicle);

    boolean removeVehicle(Vehicle vehicle);

    Optional<Vehicle> removeVehicleById(int id);
}

