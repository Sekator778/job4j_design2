package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResultFileTest {

    @Test
    public void testOne() {
        ResultFile.multiToFile("printFive", 5);
    }

}