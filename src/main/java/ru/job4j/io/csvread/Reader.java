package ru.job4j.io.csvread;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class Reader {
    private Set<String> argSet;
    private ArgsName argsName;
    private String path;
    private String delimiter;
    private String out;
    private List<String> filters;
    private final List<String> strings = new ArrayList<>();

    /**
     * с получением аргументов не совсем пока хорошо
     */
    public void exec(String[] strings) {
        argsName = ArgsName.of(strings);
        argSet = argsName.getKeys();
        testArgs();
        getCommand();
        scannerCSV2();
        if (out.equals("console")) {
            outToConsole();
        } else {
            outToFile();
        }
    }

    /**
     * считываем файл
     */
    private void scannerCSV2() {
        List<Integer> index = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            Scanner dataScanner;
            int i = 0;
            int line = 0;
            while (scanner.hasNextLine()) {
                dataScanner = new Scanner(scanner.nextLine());
                dataScanner.useDelimiter(delimiter);
                if (line == 0) {
                    String firstLine = dataScanner.nextLine();
                    index = getIndexFilter(firstLine);
                    line++;
                    continue;
                } else {
                    line++;
                }
                while (dataScanner.hasNext()) {
                    String data = dataScanner.next();
                    if (index.contains(i)) {
                        strings.add(data + delimiter);
                    }
                    i++;
                }
                strings.add(System.lineSeparator());
                i = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * достаем индексы столбцов которые нам надо вывести
     * предикат фильтра
     * заполнение результата заглавием колонок
     * с переводом строк?
     */
    private List<Integer> getIndexFilter(String firstLine) {
        List<String> first;
        List<Integer> index = new ArrayList<>();
        first = Arrays.asList(firstLine.split(", "));
        for (int i = 0; i < first.size(); i++) {
            if (filters.contains(first.get(i))) {
                index.add(i);
                strings.add(first.get(i) + delimiter);
            }
        }
        strings.add(System.lineSeparator());
        return index;
    }

    /**
     * вывод в консоль
     */
    private void outToConsole() {
        strings.forEach(System.out::print);
    }

    /**
     * вывод в файл
     */
    private void outToFile() {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(out))) {
            strings.forEach(d -> {
                try {
                    reader.write(d);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * есть ли необходимые параметры
     */
    private void testArgs() {
        Set<String> argTest = Set.of("delimiter", "out", "filter", "path");
        if (!argTest.containsAll(argSet)) {
            throw new IllegalArgumentException(
                    "-path directory, -out console or file, -filter filter columns, -delimiter delimiter CSV file");
        }
    }

    /**
     * возврат параметров из аргументов
     */
    private void getCommand() {
        path = argsName.get("path");
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filters = Arrays.asList(argsName.get("filter").split(","));
    }
}
