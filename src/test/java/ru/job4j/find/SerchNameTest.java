package ru.job4j.find;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SerchNameTest {

    @Test
    public void redicate() {
        SerchName serchName = new SerchName();
        File file = new File("test.txt");
        assertTrue(serchName.redicate("test.txt").test(file.toPath()));
        assertFalse(serchName.redicate("").test(file.toPath()));
    }
}