package ru.job4j.filefinder;

import java.io.IOException;

public class Worker {
   final static Finder FINDER = new Finder();

    public static void main(String[] args) throws IOException {
        FINDER.info();
        FINDER.init(args);
    }
}
