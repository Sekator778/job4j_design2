package ru.job4j.parking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.parking.model.Car;
import ru.job4j.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class AutoParking implements Parking {
    private List<Vehicle> autoList;
    private List<Vehicle> lorryList;
    private int freePlaysForAuto;
    private int freePlaysForLorry;
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoParking.class);

    public AutoParking(int countAuto, int countLorry) {
        this.autoList = new ArrayList<>(countAuto);
        this.lorryList = new ArrayList<>(countLorry);
        freePlaysForAuto = countAuto;
        freePlaysForLorry = countLorry;
    }

    /**
     * либо на N парковочных мест для легковых машин, стоящих рядом.
     * несовсем верно реализованно
     * если надо переделать можно используя класс плейс
     */
    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean result = false;
        if (isLorry(vehicle) && freePlaysForLorry > 0) {
            lorryList.add(vehicle);
            freePlaysForLorry--;
            result = true;
        } else if (isLorry(vehicle) && freePlaysForAuto >= vehicle.getSize()) {
            for (int i = 0; i < vehicle.getSize(); i++) {
                autoList.add(vehicle);
                freePlaysForAuto--;
            }
            result = true;
        } else if (freePlaysForAuto > 0 && !isLorry(vehicle)) {
            autoList.add(vehicle);
            freePlaysForAuto--;
            result = true;
        }
        if (result) {
            LOGGER.info("Vehicle add to parking");
        } else {
            LOGGER.info("No free places");
        }
        return result;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        boolean result;
        if (vehicle.getSize() == Car.SIZE) {
            int sizeAutoList = autoList.size();
            autoList = autoList
                    .stream()
                    .filter(auto -> !auto.equals(vehicle))
                    .collect(Collectors.toList());
            result = sizeAutoList != autoList.size();
        } else {
            int sizeLorryList = lorryList.size();
            lorryList = lorryList
                    .stream()
                    .filter(lorry -> !lorry.equals(vehicle))
                    .collect(Collectors.toList());
            result = sizeLorryList != lorryList.size();
        }
        if (result) {
            LOGGER.info("Car successfully left from parking");
        } else {
            LOGGER.info("wrong vehicle");
        }
        return result;
    }

    @Override
    public Optional<Vehicle> removeVehicleById(int id) {
        Optional<Vehicle> findVehicleInAuto = autoList.stream()
                .filter(auto -> auto.getId() == id)
                .findAny();
        Optional<Vehicle> findVehicleInLorry;
        if (findVehicleInAuto.isEmpty()) {
            findVehicleInLorry = lorryList.stream()
                    .filter(lorry -> lorry.getId() == id)
                    .findAny();
        } else {
            removeVehicle(findVehicleInAuto.get());
            return findVehicleInAuto;
        }
        if (findVehicleInLorry.isEmpty()) {
            return Optional.empty();
        } else {
            removeVehicle(findVehicleInLorry.get());
            return findVehicleInLorry;
        }
    }

    private boolean isLorry(Vehicle vehicle) {
        return vehicle.getSize() > Car.SIZE;
    }
}
