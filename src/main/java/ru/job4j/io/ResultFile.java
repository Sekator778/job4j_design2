package ru.job4j.io;

import ru.job4j.io.app.Calc;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("C:\\Temp\\result.txt")) {
            out.write("Hello, world!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void multiToFile(String nameFile, int i) {
        String multi = Calc.multi(i);
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Temp\\" + nameFile)) {
            outputStream.write(multi.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}