package ru.job4j.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CSVReaderTest {
    private final String pathFile = "./data/test.txt";

    //8. Find Pattern
    @Test
    public void whenFindPatternUsingScannerThenFound() throws IOException {
        String expectedValue = "world";
        FileInputStream inputStream = new FileInputStream(pathFile);
        Scanner scanner = new Scanner(inputStream);

        String result = scanner.findInLine("wo..d");
        assertEquals(expectedValue, result);

        scanner.close();
    }

    @Test
    public void whenFindPatternInHorizonThenFound() throws IOException {
        String expectedValue = "world";
        FileInputStream inputStream = new FileInputStream(pathFile);
        Scanner scanner = new Scanner(inputStream);

        String result = scanner.findWithinHorizon("wo..d", 5);
        assertNull(result);

        result = scanner.findWithinHorizon("wo..d", 100);
        assertEquals(expectedValue, result);

        scanner.close();
    }

    //9. Skip Pattern
    @Test
    public void whenSkipPatternUsingScannerThenSkipped() throws IOException {
        FileInputStream inputStream = new FileInputStream(pathFile);
        Scanner scanner = new Scanner(inputStream);

        scanner.skip(".e.lo");

        assertEquals("world", scanner.next());

        scanner.close();
    }

}