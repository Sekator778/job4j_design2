package ru.job4j.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TableView {
    public static void main(String[] args) throws IOException {
        String tableName = "example";
        String separator = File.separator;
        String path = "src" + separator + "main" + separator + "resources" + separator + "app.properties";
        FileReader reader = new FileReader(path);
        Properties properties = new Properties();
        properties.load(reader);
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.dropTable(tableName);
        tableEditor.createTable(tableName);
        tableEditor.addColumn(tableName, "People", "varchar(50)");
        tableEditor.addColumn(tableName, "age", "int");
        tableEditor.renameColumn(tableName, "age", "weight");
        tableEditor.dropColumn(tableName, "weight");

    }
}
