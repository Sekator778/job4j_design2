package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 65;
        char c = 'A';
        short s = 65;
        int i  = 65;
        long  l = 65L;
        float f = 54f;
        double d = 65.56;
        boolean b1 = true;

        LOG.debug("We have 8 primitive: byte : {}, char: {}, short: {}, int: {}, long: {}, " +
                "float: {}, double: {}, boolean: {} ", b , c, s, i, l, f, d, b1);
    }
}