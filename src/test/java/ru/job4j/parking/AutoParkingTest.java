package ru.job4j.parking;

import org.junit.Test;
import ru.job4j.parking.model.Car;
import ru.job4j.parking.model.Lorry;
import ru.job4j.parking.model.Vehicle;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AutoParkingTest {

    @Test
    public void whenAddVehicleCarAndAvailableCarPlaces() {
        Car car = new Car(1);
        AutoParking parking = new AutoParking(1, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndAvailableLorryPlaces() {
        Car car = new Car(2);
        AutoParking parking = new AutoParking(0, 1);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndNoAvailablePlaces() {
        Car car = new Car(3);
        AutoParking parking = new AutoParking(0, 0);
        parking.addVehicle(car);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2LorryPlace() {
        Vehicle car = new Lorry(1, 2);
        AutoParking parking = new AutoParking(0, 1);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3LorryPlace() {
        Vehicle car = new Lorry(1, 3);
        AutoParking parking = new AutoParking(0, 1);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2CarPlace() {
        Vehicle car = new Lorry(1, 2);
        AutoParking parking = new AutoParking(2, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3CarPlace() {
        Vehicle car = new Lorry(1, 3);
        AutoParking parking = new AutoParking(3, 0);
        assertTrue(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2AndNoAvailablePlaces() {
        Vehicle car = new Lorry(10, 2);
        AutoParking parking = new AutoParking(0, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3AndNoAvailablePlaces() {
        Vehicle car = new Lorry(11, 3);
        AutoParking parking = new AutoParking(0, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize2AndAvailable1CarPlace() {
        Vehicle car = new Lorry(12, 2);
        AutoParking parking = new AutoParking(1, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleLorrySize3AndAvailable2CarPlace() {
        Vehicle car = new Lorry(13, 3);
        AutoParking parking = new AutoParking(2, 0);
        assertFalse(parking.addVehicle(car));
    }

    @Test
    public void whenAddVehicleCarAndLorrys() {
        Vehicle auto = new Car(7);
        Vehicle lorry2 = new Lorry(7, 2);
        Vehicle lorry3 = new Lorry(7, 3);
        AutoParking parking = new AutoParking(6, 2);
        parking.addVehicle(auto);
        parking.addVehicle(lorry2);
        parking.addVehicle(lorry3);
        parking.addVehicle(lorry2);
        assertTrue(parking.addVehicle(lorry3));
        assertFalse(parking.addVehicle(lorry3));
    }

    @Test
    public void whenRemoveAutoByIDExpectAutoWithIdAndTwoRemoveAutoByIdResultOptionalEmpty() {
        int id = 777;
        Vehicle auto = new Car(id);
        AutoParking parking = new AutoParking(6, 0);
        parking.addVehicle(auto);
        Optional<Vehicle> vehicle = parking.removeVehicleById(id);
        if (vehicle.isPresent()) {
            assertThat(vehicle.get().getId(), is(id));

            assertEquals(vehicle.get(), auto);
        }
        assertTrue(parking.removeVehicleById(id).isEmpty());
    }

    @Test
    public void removeAutoExpectTrueSecondRemoveExpectFalse() {
        Vehicle auto = new Car(7);
        AutoParking parking = new AutoParking(6, 2);
        parking.addVehicle(auto);

        assertTrue(parking.removeVehicle(auto));
        assertFalse(parking.removeVehicle(auto));
    }

    @Test
    public void whenRemoveLorryExpectLorry() {
        Vehicle lorry = new Lorry(23, 4);
        AutoParking parking = new AutoParking(0, 1);
        parking.addVehicle(lorry);

        assertTrue(parking.removeVehicle(lorry));
        assertFalse(parking.removeVehicle(lorry));
    }

    @Test
    public void whenRemoveLorryByIDExpectLorryWithIdAndTwoRemoveLorryByIdResultOptionalEmpty() {
        int id = 4;
        Vehicle lorry = new Lorry(id, 5);
        AutoParking parking = new AutoParking(3, 7);
        parking.addVehicle(lorry);
        Optional<Vehicle> vehicle = parking.removeVehicleById(id);
        if (vehicle.isPresent()) {
            assertThat(vehicle.get().getId(), is(id));

            assertEquals(vehicle.get(), lorry);
        }
        assertTrue(parking.removeVehicleById(id).isEmpty());
    }

    @Test
    public void whenRemoveSameIdAutoAndLorryWeExpectTrue() {
        int id = 23;
        int diff = 100_000;
        Vehicle auto = new Car(id);
        Vehicle lorry = new Lorry(id, 4);
        AutoParking parking = new AutoParking(10, 10);

        assertTrue(parking.addVehicle(auto));
        assertTrue(parking.addVehicle(lorry));

        Optional<Vehicle> vehicleOne = parking.removeVehicleById(id + diff);
        Optional<Vehicle> vehicleTwo = parking.removeVehicleById(id);

        vehicleOne.ifPresent(vehicle -> assertEquals(vehicle, auto));
        vehicleTwo.ifPresent(vehicle -> assertEquals(vehicle, lorry));
    }
}