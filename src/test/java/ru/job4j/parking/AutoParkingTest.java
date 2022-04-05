package ru.job4j.parking;

import org.junit.Test;
import ru.job4j.parking.model.Car;
import ru.job4j.parking.model.Lorry;
import ru.job4j.parking.model.Vehicle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AutoParkingTest {

    @Test
    public void whenAddVehicleCarAndAvailableCarPlaces() {
        Car car = new Car();
        AutoParking parking = new AutoParking(1, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndAvailableLorryPlaces() {
        Car car = new Car();
        AutoParking parking = new AutoParking(0, 1);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndNoAvailablePlaces() {
        Car car = new Car();
        AutoParking parking = new AutoParking(0, 0);
        parking.addVehicle(car);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2LorryPlace() {
        Vehicle car = new Lorry(2);
        AutoParking parking = new AutoParking(0, 1);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3LorryPlace() {
        Vehicle car = new Lorry(3);
        AutoParking parking = new AutoParking(0, 1);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2CarPlace() {
        Vehicle car = new Lorry(2);
        AutoParking parking = new AutoParking(2, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3CarPlace() {
        Vehicle car = new Lorry(3);
        AutoParking parking = new AutoParking(3, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2AndNoAvailablePlaces() {
        Vehicle car = new Lorry(2);
        AutoParking parking = new AutoParking(0, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3AndNoAvailablePlaces() {
        Vehicle car = new Lorry(3);
        AutoParking parking = new AutoParking(0, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2AndAvailable1CarPlace() {
        Vehicle car = new Lorry(2);
        AutoParking parking = new AutoParking(1, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3AndAvailable2CarPlace() {
        Vehicle car = new Lorry(3);
        AutoParking parking = new AutoParking(2, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndLorrys() {
        Vehicle auto = new Car();
        Vehicle lorry2 = new Lorry(2);
        Vehicle lorry3 = new Lorry(3);
        AutoParking parking = new AutoParking(6, 2);
        parking.addVehicle(auto);
        parking.addVehicle(lorry2);
        parking.addVehicle(lorry3);
        parking.addVehicle(lorry2);
        assertTrue(parking.addVehicle(lorry3));
        assertFalse(parking.addVehicle(lorry3));
    }
}