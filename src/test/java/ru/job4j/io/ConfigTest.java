package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Alex"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithoutException() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithDouble() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("double"), is("Alex"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenPairWithSecondParam() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("url"), is("jdbc=hi all"));
    }

    @Test
    public void whenPairWithoutSecondParam() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("url2"), is("jdbchiall"));
    }
}
