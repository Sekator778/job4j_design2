package ru.job4j.filefinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Finder {
    private static final Logger LOG = LoggerFactory.getLogger(Finder.class.getName());
    public static List<Path> listFiles = new ArrayList<>();

    /**
     * вывод на экран подсказки
     */
    public void info() {
        System.out.println("Ключи \n -d - директория, в которой начинать поиск.\n -n - имя файла, маска, либо регулярное выражение.\n  -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.\n -o - результат записать в файл.");
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            LOG.error("Error ->>>>");
            e.printStackTrace();
        }
    }

    public void init(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Enter: -d directory, -n predicate, -t type search, -o output file with result"
            );
        }
        ArgsName argsMap = ArgsName.of(args);
        Set<String> argSet = argsMap.getKeys();
        for (String key : argSet) {
            if (!"d".equals(key) && !"n".equals(key) && !"t".equals(key) && !"o".equals(key) || argSet.size() != 4) {
                throw new IllegalArgumentException("-d directory, -n predicate, -t type search, -o output");
            }
        }
        switch (argsMap.get("t")) {
            case "mask":
                getMask(argsMap);
                break;
            case "name":
                getName(argsMap);
                break;
            case "regex":
                getRegex(argsMap);
                break;
            default:
                System.out.println("invalid param n");
                break;
        }
        String fileName = argsMap.get("o");
        writeToFile(fileName);
    }

    /**
     * по регулярному выражению
     */
    private void getRegex(ArgsName argsMap) throws IOException {
        listFiles = Search.search(Path.of(argsMap.get("d")), p -> p.toFile().getName().matches(argsMap.get("n")));
    }

    /**
     * по полному совпадению
     */
    private void getName(ArgsName argsMap) throws IOException {
        listFiles = Search.search(Path.of(argsMap.get("d")), p -> p.toFile().getName().equals(argsMap.get("n")));
    }

    /**
     * по маске
     */
    private void getMask(ArgsName argsMap) throws IOException {
        listFiles = Search.search(Path.of(argsMap.get("d")), p -> p.toFile().getName().endsWith(argsMap.get("n")));
    }

    /**
     * запись в файл
     */
    private void writeToFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        for (Path p : listFiles) {
            try {
                writer.write(String.valueOf(p.getFileName()));
                writer.write("\n");
            } catch (IOException e) {
                LOG.error("Error write file --->>>");
                e.printStackTrace();
            }
        }
        writer.flush();
        writer.close();
    }

    /**
     * для теста вывод в консоль
     */
    private void view() {
        listFiles.forEach(file -> System.out.println(file.getFileName()));
    }
}
