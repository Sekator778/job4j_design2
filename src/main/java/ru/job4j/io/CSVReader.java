package ru.job4j.io;

import java.io.*;
import java.util.*;

/**
 * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
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
        if (out.equals("console")) {
            outToConsole();
        } else if (out.equals("file")) {
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
                        STRINGS.add(data + "|");
                    }
                    i++;
                }
                STRINGS.add("\n");
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
                STRINGS.add(first.get(i) + "|");
            }
        }
        STRINGS.add("\n");
        return index;
    }

    /**
     * вывод в консоль
     */
    private static void outToConsole() {
        scannerCSV2().forEach(System.out::print);
    }

    /**
     * вывод в файл
     */
    private static void outToFile() {
        List<String> outData = scannerCSV2();
        Random random = new Random(10);
        try (BufferedWriter reader = new BufferedWriter(new FileWriter(out + random.nextInt() + ".txt"))) {
            for (String line : outData) {
                reader.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * есть ли необходимые параметры
     */
    private static void testArgs() {
        for (String key : argSet) {
            if (!"delimiter".equals(key) && !"out".equals(key) && !"filter".equals(key) && !"path".equals(key)) {
                throw new IllegalArgumentException(
                        "-path directory, -out console or file, -filter filter columns, -delimiter delimiter CSV file");
            }
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
