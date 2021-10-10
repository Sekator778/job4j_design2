package ru.job4j.filefinder;

import ru.job4j.io.ArgsName;

import java.util.Set;

/**
 * валидация параметров запуска
 */
public class Validate {
    public  static ArgsName validArgs(String[] args) {
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
        return argsMap;
    }
}
