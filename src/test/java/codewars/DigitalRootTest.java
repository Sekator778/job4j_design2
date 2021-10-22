package codewars;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitalRootTest {

    @Test
    public void test1() {
        assertEquals("Nope!", 7, DigitalRoot.digitalRoot(16));
    }

    @Test
    public void test2() {
        assertEquals("Nope!", 6, DigitalRoot.digitalRoot(456));
    }
}