package ru.job4j.set;

import org.junit.Test;
import ru.job4j.map.Map;
import ru.job4j.map.SimpleMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void thenGetNull() {
        Map<String, Integer> map = new SimpleMap<>();
        assertNull(map.get("test"));
    }

    @Test
    public void thenRemTrue() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        assertTrue(map.remove("test"));
        assertTrue(map.put("test", 2));
        assertThat(map.get("test"), is(2));
    }

    @Test
    public void thenRemFalse() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("test", 1);
        assertFalse(map.remove(null));
    }

}