package ru.job4j.io;

import java.io.*;
import java.util.*;

/**
 * java -jar target/CSVReader.jar -path=./data/forScanner.csv -delimiter=","  -out=console -filter=name,age
 */
public class CSVReader {
    private static Set<String> argSet;
    private static ArgsName argsName;
    private static String path;
    private static String delimiter;
    private static String out;
    private static List<String> filters;
    private static final List<String> STRINGS = new ArrayList<>();

    /**
     * с получением аргументов не совсем пока хорошо
     */
    public static void main(String[] args) throws FileNotFoundException {
        argsName = ArgsName.of(args);
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
    private static List<String> scannerCSV2() {
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
                        STRINGS.add(data + delimiter);
                    }
                    i++;
                }
                STRINGS.add(System.lineSeparator());
                i = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return STRINGS;
    }

    /**
     * достаем индексы столбцов которые нам надо вывести
     * предикат фильтра
     * заполнение результата заглавием колонок
     * с переводом строк?
     */
    private static List<Integer> getIndexFilter(String firstLine) {
        List<String> first;
        List<Integer> index = new ArrayList<>();
        first = Arrays.asList(firstLine.split(", "));
        for (int i = 0; i < first.size(); i++) {
            if (filters.contains(first.get(i))) {
                index.add(i);
                STRINGS.add(first.get(i) + delimiter);
            }
        }
        STRINGS.add(System.lineSeparator());
        return index;
    }

    /**
     * вывод в консоль
     */
    private static void outToConsole() {
        STRINGS.forEach(System.out::print);
    }

    /**
     * вывод в файл
     */
    private static void outToFile() {
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(out))) {
            STRINGS.forEach(d -> {
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
    private static void testArgs() {
        Set<String> argTest = Set.of("delimiter", "out", "filter", "path");
        if (!argTest.containsAll(argSet)) {
            throw new IllegalArgumentException(
                    "-path directory, -out console or file, -filter filter columns, -delimiter delimiter CSV file");
        }
    }

    /**
     * возврат параметров из аргументов
     */
    private static void getCommand() {
        path = argsName.get("path");
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filters = Arrays.asList(argsName.get("filter").split(","));
    }
}
