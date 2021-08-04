package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static String readFile(String nameFile) {
        StringBuilder builder = new StringBuilder();
        int ch;
        try (FileInputStream input = new FileInputStream("C:\\Temp\\" + nameFile)) {
            while ((ch = input.read()) != -1) {
                builder.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
