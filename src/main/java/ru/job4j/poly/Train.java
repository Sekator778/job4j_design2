package ru.job4j.poly;

public class Train implements Transport{
    private final int MAX_SPEED;

    public Train(int MAX_SPEED) {
        this.MAX_SPEED = MAX_SPEED;
    }

    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " - The train moves on rails.");
    }

    @Override
    public void speed() {
        System.out.printf("I move at the speed of %d miles per hour", MAX_SPEED);

    }
}
