package ru.job4j.parking;

import ru.job4j.parking.model.Car;
import ru.job4j.parking.model.Lorry;
import ru.job4j.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AutoParking implements Parking {
    private final List<Vehicle> autoList;
    private final List<Vehicle> lorryList;
    private int freePlaysForAuto;
    private int freePlaysForLorry;

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
            result = true;
        }
        if (result) {
            System.out.println("Vehicle add to parking");
        } else {
            System.out.println("No free places");
        }
        return result;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        boolean result = false;
        if (vehicle.getSize() == Car.SIZE) {
            for (int i = 0; i < autoList.size(); i++) {
                if (autoList.get(i).equals(vehicle)) {
                    autoList.set(i, null);
                    result = true;
                }
            }
        } else {
            for (int i = 0; i < lorryList.size(); i++) {
                if (lorryList.get(i).equals(vehicle)) {
                    lorryList.set(i, null);
                    result = true;
                }
            }
        }
        if (result) {
            System.out.println("Car successfully left from parking");
        } else {
            System.out.println("wrong vehicle");
        }
        return result;
    }

    private boolean isLorry(Vehicle vehicle) {
        return vehicle.getSize() > Car.SIZE;
    }
}
