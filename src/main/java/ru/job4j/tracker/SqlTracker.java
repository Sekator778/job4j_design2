package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.model.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private  Connection connection;
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());


    public SqlTracker() {
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("className"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("login"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        Item rsl = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into items (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            int rows = preparedStatement.executeUpdate();
/*            System.out.printf("%d rows added \n", rows);*/
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
                rsl = item;
            } else {
                throw new SQLException("Not key for Item");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement statement = connection.prepareStatement("update items set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            int rows = statement.executeUpdate();
            if (rows == 1) {
                rsl = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            int i = statement.executeUpdate();
            rsl = i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select items.id, name from items")) {
            ResultSet set = statement.executeQuery();
            rsl = findSet(statement);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * тут дубль кода пишет если будет верно надо вынести в метод который в лист инфу тащит по statement.executeQuery()
     * statement.executeQuery() =  норм метод уже писал он дает нам множество а это норм там есть все
     *
     * @param key кей тут имя итема выступает ключем для поиска
     * @return лист итемов
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select items.id, name from items where name = ?")) {
            statement.setString(1, key);
            rsl = findSet(statement);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }


    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement = connection.prepareStatement("select items.id, name from items where id = ?")) {
            statement.setInt(1, id);
            List<Item> rsl = findSet(statement);
            if (!rsl.isEmpty()) {
                item = rsl.get(0);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * вынес то что писал код повторяеться
     * statement.executeQuery() =  норм метод уже писал он дает нам множество а это норм там есть все
     *
     * @param statement для выполнения запросов четкий класс PreparedStatement
     * @return лист
     */
    private List<Item> findSet(PreparedStatement statement) {
        List<Item> result = new ArrayList<>();
        try (ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                Item item = new Item(name);
                item.setId(id);
                result.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}