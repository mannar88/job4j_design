package ru.job4j.find;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SearchMaskTest {
    @Test
    public void preedStar() {
        SearchMask searchMask = new SearchMask();
        File file = new File("Новый текстовый документ.txt");
        assertTrue(searchMask.redicate("*.txt").test(file.toPath()));
        assertTrue(searchMask.redicate("Нов*").test(file.toPath()));
        assertFalse(searchMask.redicate("*csv").test(file.toPath()));
        assertFalse(searchMask.redicate("test*").test(file.toPath()));
        assertTrue(searchMask.redicate("Но*xt").test(file.toPath()));
    }

    @Test
    public void preedQuestion() {
        SearchMask searchMask = new SearchMask();
        File file = new File("Новый текстовый документ.txt");
        assertTrue(searchMask.redicate("?овый текстовый документ.txt").test(file.toPath()));
        assertTrue(searchMask.redicate("Новый текстовый?документ.txt").test(file.toPath()));
        assertFalse(searchMask.redicate("Новый текстовый? документ.txt").test(file.toPath()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ellArg() {
        SearchMask searchMask = new SearchMask();
        searchMask.redicate("name");
    }

}