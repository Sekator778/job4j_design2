package ru.job4j.io.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {
    @Test
    public void whenPrintToMonitor() {
        String multi = Calc.multi(5);
        System.out.println(multi);
    }

}