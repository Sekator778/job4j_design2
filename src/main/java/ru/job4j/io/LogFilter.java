package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * вариант
 * List<String> petNames = humans.stream()
 * .map(human -> human.getPets()) //преобразовываем Stream<Human> в Stream<List<Pet>>
 * .flatMap(pets -> pets.stream())//"разворачиваем" Stream<List<Pet>> в Stream<Pet>
 * .collect(Collectors.toList());
 */
public class LogFilter {
    public static List<String> filter(String file) {


        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Temp\\" + file))) {
            while (reader.ready()) {
                String line = reader.readLine();
                var chars = line.split(" ");
                if (chars[chars.length - 2].contains("404")) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        log.forEach(System.out::println);
    }
}