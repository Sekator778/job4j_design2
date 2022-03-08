package ru.job4j.poly;

public class PolyUsage {
    public static void main(String[] args) {
        Transport bus1 = new Bus(100);
        Transport bus2 = new Bus(75);
        Transport bus3 = new Bus(55);

        Transport train1 = new Train(150);
        Transport train2 = new Train(250);
        Transport train3 = new Train(350);

        Transport plane1 = new Plane(400);
        Transport plane2 = new Plane(500);
        Transport plane3 = new Plane(600);

        Transport[] transports = new Transport[]{bus1, bus2, bus3,
                train1, train2, train3,
                plane1, plane2, plane3};

        for (Transport transport : transports) {
            transport.move();
            transport.speed();
        }
    }
}
