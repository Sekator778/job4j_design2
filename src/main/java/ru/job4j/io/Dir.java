package ru.job4j.io;

import java.io.File;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subfield : Objects.requireNonNull(file.listFiles())) {
            if (subfield.isFile()) {
                System.out.printf("File name %s, size = %s \n", subfield.getName(), subfield.length());
            }
        }
    }
}