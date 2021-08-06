package ru.job4j.io.arch;
import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static List<Path> listFiles = new ArrayList<>();

    public void packFiles(List<Path> source, Path target) {
        for (Path file : source) {
            packSingleFile(file, target);
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(String.valueOf(source))
            )) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "Enter: -d directory for archiving, -e exclude file, -o output archive file"
            );
        }
        ArgsName argsMap = ArgsName.of(args);
        Set<String> argSet = argsMap.getKeys();
        for (String key : argSet) {
            if (!"d".equals(key) && !"e".equals(key) && !"o".equals(key)) {
                throw new IllegalArgumentException("-d directory, -e exclude, -o output");
            }
        }
        listFiles = Search.search(Path.of(argsMap.get("d")), p -> !p.toFile().getName().endsWith(argsMap.get("e")));
        new Zip().packFiles(listFiles, Path.of(argsMap.get("o")));
    }
}