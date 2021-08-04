package ru.job4j.io.app;

public class Calc {
    public static String multi(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                builder.append((i + 1) * (j + 1)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
