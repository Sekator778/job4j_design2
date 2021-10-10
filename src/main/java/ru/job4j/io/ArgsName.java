package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * программу, которая принимает массив параметров и разбивает их на пары: ключ, значение.
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("NO args !!!");
        }
        for (String s : args
        ) {
            var strings = s.split("=");
            if (strings.length != 2) {
                throw new IllegalArgumentException("args invalid: Example key=value");
            }
            values.put(strings[0].substring(1), strings[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }

    public Set<String> getKeys() {
        return values.keySet();
    }
}