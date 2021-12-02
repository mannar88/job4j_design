package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertThat(input, is(expected));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddForeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 3, 2);
    }

    @Test
    public void thenRemoveIf() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }
        Predicate<Integer> filtr = integer -> integer % 2 == 0;
        ListUtils.removeIf(list, filtr);
        assertThat(list, is(Arrays.asList(1, 3)));
    }

    @Test
    public void thenReplaceIf() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);
        Predicate<Integer> filtr = integer -> integer == 2;
        ListUtils.replaceIf(list, filtr, 0);
        assertThat(list, is(Arrays.asList(1, 0, 1, 0)));
    }

    @Test
    public void thenRemoveAll() {
        List<Integer> list = new ArrayList<>();
        List<Integer> elementes = List.of(6, 7, 8, 9, 10);
        for (int i = 1; i < 11; i++) {
            list.add(i);
        }
        ListUtils.removeAll(list, elementes);
        assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5)));
    }

}