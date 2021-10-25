package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String separator = File.separator;
        Config config = new Config("src" + separator + "main" + separator + "resources" + separator + "app.properties");
        Class.forName(config.value("className"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getDatabaseProductName());
            System.out.println(metaData.getIdentifierQuoteString());
            System.out.println(metaData.getURL());
        }
    }
}
