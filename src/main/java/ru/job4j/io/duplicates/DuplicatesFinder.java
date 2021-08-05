package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * если собрали в JAR  командой  mvn package
 * то можем запустить
 * use java -jar target/duplicatesFinder.jar  param1
 * where param1 - path for looking
 */
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of(args[0]), new DuplicatesVisitor());
    }
}