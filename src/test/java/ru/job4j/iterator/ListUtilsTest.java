package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void removeValueIfIsNumberAndIsEqual0() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 0, 2));
        Predicate<Integer> filter = value -> value == 0;
        ListUtils.removeIf(input, filter);
        assertThat(input, is(Arrays.asList(1, 2)));
    }

    @Test
    public void replaceValueTo5IfIsNumberAndIsEqual0() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 0, 2, 10, 0));
        Predicate<Integer> filter = value -> value == 0;
        ListUtils.replaceIf(input, filter, 5);
        assertThat(input, is(Arrays.asList(5, 1, 5, 2, 10, 5)));
    }

    @Test
    public void removeListOfNumbersFromAnotherListOfNumbers() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 0, 2, 1, 3, 5, 1));
        List<Integer> removeList = new ArrayList<>(Arrays.asList(0, 1));
        ListUtils.removeAll(input, removeList);
        assertThat(input, is(Arrays.asList(2, 3, 5)));
    }
}
