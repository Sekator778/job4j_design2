package ru.job4j.spammer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ImportDBTest {
    static Connection connection;
    static Properties config;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = ImportDBTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() throws IOException {
        ImportDB importDB = new ImportDB(config, "src/main/java/ru/job4j/spammer/dump.txt");
        User user = new User("Alex", "asd123@mail.op");
        importDB.load().forEach(System.out::println);
    }
}