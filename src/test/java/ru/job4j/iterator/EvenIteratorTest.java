package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenIteratorTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void shouldRemovePreviousElementWhenFindNextElementFarFromPrevious() {
        it = new EvenNumbersIterator(new int[]{2, 1, 4});
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        it.remove();
        Iterator<Integer> it1 = it;
        assertThat(it1.next(), is(4));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldRemoveSecondElement() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        it.remove();
        Iterator<Integer> it1 = it;
        assertThat(it1.next(), is(4));
        assertThat(it1.hasNext(), is(true));
        assertThat(it1.next(), is(6));
        assertThat(it1.hasNext(), is(false));
        it1.remove();
        it1.remove();
    }
}