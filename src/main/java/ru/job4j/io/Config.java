package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * считать все ключи в карту values
     */
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                String line = reader.readLine();
                var strings = line.split("=");
                if (line.contains("=") && strings.length == 2) {
                    values.put(strings[0], strings[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.isEmpty()) {
            load();
        }
        if (values.containsKey(key)) {
            return values.get(key);
        } else {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.add("B Y E");
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}