package ru.job4j.io;

public class EvenNumberFile {
    public static void main(String[] args) {
        String readFile = ReadFile.readFile("even.txt");
        System.out.println(readFile);
    }
}