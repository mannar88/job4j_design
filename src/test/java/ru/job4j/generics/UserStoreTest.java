package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {
    private ru.job4j.generics.UserStore userStore = new ru.job4j.generics.UserStore();

    @Before
    public void thenAdd() {

        for (int i = 1; i < 11; i++) {
            userStore.add(new ru.job4j.generics.User(i + ""));
        }
    }

    @Test
    public void thenFindById() {
        ru.job4j.generics.User user1 = userStore.findById("1");
        ru.job4j.generics.User user5 = userStore.findById("5");
        User user10 = userStore.findById("10");
        assertThat(user1.getId(), is("1"));
        assertThat(user5.getId(), is("5"));
        assertThat(user10.getId(), is("10"));
    }

    @Test(expected = NullPointerException.class)
    public void thenFindByIdNull() {
        User userNull = userStore.findById(null);
    }

    @Test
    public void thenDelete() {
        assertTrue(userStore.delete("6"));
        assertFalse(userStore.delete("6"));
    }

    @Test
    public void thenReplaers() {
        assertTrue(userStore.replace("4", new User("44")));
        assertFalse(userStore.replace("11", new User("111")));
    }

}