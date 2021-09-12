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
     * готовые аргументы
     */
    public void exec(ArgsName args) {
        argsName = args;
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
                cutLastChar();
                if (!strings.isEmpty()) {
                    strings.add(System.lineSeparator());
                }
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
        first = Arrays.asList(firstLine.split(delimiter));
        for (int i = 0; i < first.size(); i++) {
            if (filters.contains(first.get(i))) {
                index.add(i);
                strings.add(first.get(i) + delimiter);
            }
        }
        cutLastChar();
        if (!strings.isEmpty()) {
            strings.add(System.lineSeparator());
        }
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
     * проверяем
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

    /**
     * обрезать в конце разделитель
     * тут надо модифицировать программу
     * возможно замена на  do --> while
     */
    private void cutLastChar() {
        if (!strings.isEmpty()) {
            String replace = strings.get(strings.size() - 1);
            replace = replace.substring(0, replace.length() - 1);
            strings.set(strings.size() - 1, replace);
        }
    }

    /**
     * может чего показать надо для отладки
     */
    private void view() {
        for (String s : argSet
        ) {
            System.out.println(s);
            System.out.println(argsName.get(s));
        }
        System.out.println("-filters-");
        for (String f : filters
        ) {
            System.out.println(f);
        }
    }
}
