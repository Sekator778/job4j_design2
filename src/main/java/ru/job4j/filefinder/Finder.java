package ru.job4j.filefinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * получаем аргументы
     * строим условие
     * заполняем коллекцию отобранными файлами  с помощью поисковика
     * записываем в файл
     */
    public void init(String[] args) throws IOException {
        ArgsName argsMap = Validate.validArgs(args);
        Predicate<Path> predicate = buildPredicate((argsMap.get("t")), argsMap.get("n"));
        listFiles = Search.search(Path.of(argsMap.get("d")), predicate);

        String fileName = argsMap.get("o");
        writeToFile(fileName);
    }

    /**
     * постройка условия
     *
     * @param type как искать
     * @param name что искать
     * @return предикат
     */
    private Predicate<Path> buildPredicate(String type, String name) {
        return switch (type) {
            case "name" -> path -> name.equals(path.getFileName().toString());
            case "mask" -> path -> {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    switch (name.charAt(i)) {
                        case '*' -> builder.append(".*");
                        case '?' -> builder.append(".{1}");
                        case '.' -> builder.append("\\.");
                        default -> builder.append(name.charAt(i));
                    }
                }
                Pattern pattern = Pattern.compile(builder.toString());
                Matcher matcher = pattern.matcher(path.toFile().getName());
                return matcher.matches();
            };
            case "regex" -> path -> {
                Pattern pattern = Pattern.compile(name);
                Matcher matcher = pattern.matcher(path.toFile().getName());
                return matcher.matches();
            };
            default -> null;
        };
    }

    /**
     * запись в файл
     */
    private void writeToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Path p : listFiles) {
            try {
                writer.write(String.valueOf(p.toString()));
                writer.newLine();
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
