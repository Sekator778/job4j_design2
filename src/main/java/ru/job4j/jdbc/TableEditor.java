package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(TableEditor.class.getName());
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        Class.forName(properties.getProperty("className"));
        final String url = properties.getProperty("url");
        final String login = properties.getProperty("login");
        final String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            catchBlock(e, "error initConnection ->");
        }
    }

    /*создает пустую таблицу без столбцов с указанным именем;*/
    public void createTable(String tableName) {
        String sql = String.format("create table if not exists %s()", tableName);
        executeSql(sql, "----table created----");
    }

    /* dropTable() – удаляет таблицу по указанному имени;*/
    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
        executeSql(sql, "----table dropped----");
    }

    /*  - addColumn() – добавляет столбец в таблицу*/
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD %s  %s", tableName, columnName, type);
        executeSql(sql, "----add column----");
        viewTable(tableName);
    }

    /*- dropColumn() – удаляет столбец из таблицы; */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s drop column %s", tableName, columnName);
        executeSql(sql, "----drop column----");
        viewTable(tableName);
    }

    /* - renameColumn() – переименовывает столбец.*/
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s rename column %s to %s", tableName, columnName, newColumnName);
        executeSql(sql, "----rename column----");
        viewTable(tableName);
    }

    /**
     * метод выполняет запрос
     */
    private void executeSql(String sql, String log) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            LOG.info(log);
        } catch (Exception e) {
            catchBlock(e, "Error executeSql");
        }
    }

    /**
     * презентация таблицы в псевдографике
     */
    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        LOG.info("----view table----");
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    /**
     * обработка ошибок
     */
    private void catchBlock(Exception e, String s) {
        LOG.error(s);
        e.printStackTrace();
    }

    /**
     * вывод таблицы на консоль
     */
    private void viewTable(String tableName) {
        try {
            System.out.println(getTableScheme(connection, tableName));
        } catch (Exception e) {
            catchBlock(e, "Error render table to console ");
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}