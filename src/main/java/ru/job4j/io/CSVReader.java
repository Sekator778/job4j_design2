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
    private static String[] filters;

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
    private static List<String> scannerCSV() throws FileNotFoundException {
        List<String> output = new ArrayList<>();
        try (var scanner = new Scanner(new File(path))) {
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                output.add(scanner.next() + " | ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    /**
     * оставить только те что по фильтру
     */
    private  static List<String> filterCsv() throws FileNotFoundException {
        List<String> result = new ArrayList<>();
        result = scannerCSV();
        System.out.println("==================");
        System.out.println(result.get(0));
        return null;
    }



    /**
     * вывод в консоль
     */
    private static void outToConsole() throws FileNotFoundException {
        scannerCSV().forEach(System.out::print);
    }

    /**
     * вывод в файл
     */
    private static void outToFile() throws FileNotFoundException {
        List<String> outData = scannerCSV();
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
        filters = argsName.get("filter").split(",");
    }
}
