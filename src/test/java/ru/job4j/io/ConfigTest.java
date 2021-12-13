package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void thenTest() {
        String path = "./data/test.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("name"));
        assertThat(config.value("surname"), is("surname"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void thenIllegalArgumentExceptionNotKey() throws IllegalArgumentException {
        String path = "./data/test_IllegalArgumentException.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void thenIllegalArgumentExceptionNotValues() {
        String path = "./data/text_IllegalArgumentException_Not_Values.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void thenIllegalArgumentExceptionNotValid() {
        String path = "./data/test_IllegalArgumentException_==.properties";
        Config config = new Config(path);
        config.load();
    }

}