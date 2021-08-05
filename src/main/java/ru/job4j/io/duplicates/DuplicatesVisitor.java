package ru.job4j.io.duplicates;

import ru.job4j.io.duplicates.model.FileProperty;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * код, чтоб он находил дубликаты.
 * Использовать модель FileProperty.
 * Подумайте какие структуры данных лучше подойдут для решения этого задания
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> base = new HashSet<>();
    private final List<String> duplicates = new ArrayList<>();
    private final Random rnd = new Random();
    private final String filename = "duplicates" + rnd.nextInt() + ".txt";

    /**
     * вставляем в сет  модельФайла
     * если не вставился значит есть таковой то есть дубликат и его выводи
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        FileProperty candidate = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!base.add(candidate)) {
            duplicates.add(String.format("File name %s, size = %s  kb \n", file.getFileName().toString(), file.toFile().length()));
            recordDuplicateToFile();
            System.out.printf("File name %s, size = %s \n", file.getFileName().toString(), file.toFile().length());
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * записываем инфу в файл о найденных дубликатах
     */
    private void recordDuplicateToFile() {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(filename))) {
            duplicates.forEach(out::write);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}