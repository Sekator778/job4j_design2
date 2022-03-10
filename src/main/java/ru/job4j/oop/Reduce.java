package ru.job4j.oop;

public class Reduce {
    private int[] innerArray;

    public void to(int[] array) {
        innerArray = array;
    }

    public void print() {
        for (int index = 0; index < innerArray.length; index++) {
            System.out.println(innerArray[index]);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        Reduce reduce = new Reduce();
        reduce.to(array);
        reduce.print();
    }
}