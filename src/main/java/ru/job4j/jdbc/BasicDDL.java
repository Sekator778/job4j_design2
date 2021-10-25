package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.io.Config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public class BasicDDL {
    private static final String SEPARATOR = File.separator;
    private static final Logger LOG = LoggerFactory.getLogger(BasicDDL.class.getName());

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Config config = new Config("src" + SEPARATOR + "main" + SEPARATOR + "resources" + SEPARATOR + "app.properties");
        Class.forName(config.value("className"));
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) {
        String tableName = "demo_table";
        String sqlCreateTable = String.format(
                "create table if not exists %s (%s, %s);",
                tableName,
                "id serial primary key",
                "name varchar(255)"
        );
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlCreateTable);
                String tableScheme = getTableScheme(connection, tableName);
                System.out.println(tableScheme);
            }

        } catch (SQLException | ClassNotFoundException e) {
            LOG.error("method main error: -->");
            e.printStackTrace();
        }
    }

    private static String getTableScheme(Connection connection, String tableName) {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 2", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        } catch (Exception e) {
            LOG.error("Get Table Scheme Method error ->");
            e.printStackTrace();
        }
        return buffer.toString();
    }

    private static void insertIntoTable(String tableName, Connection connection, String name) {
        try (var statement = connection.createStatement()) {
            var insert = statement.execute(String.format(
                    "insert into %s(name) values ('%s')", tableName, name
            ));
        } catch (Exception e) {
            LOG.error("Get Table Scheme Method error ->");
            e.printStackTrace();
        }
    }
}
