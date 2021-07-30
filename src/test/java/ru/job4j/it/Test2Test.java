package ru.job4j.it;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Test2Test {
    @Test
    public void when4El() {
        int[][] in = {
                {1}, {2, 3}
        };
        Test2 it = new Test2(in);
        System.out.println("in length " + in.length);
        System.out.println("in[0] length " + in[0].length);
    }

}