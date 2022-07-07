package ru.job4j.map;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutFirstValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(0, 1));
    }

    @Test
    public void whenPutSameValues() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(0, 1));
        assertFalse(map.put(0, 1));
    }

    @Test
    public void whenGetValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(0, 1));
        assertThat(map.get(0),  Is.is(1));
    }

    @Test
    public void whenGetAbsentKeyValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertNull(map.get(10));
    }

    @Test
    public void whenRemoveValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertTrue(map.remove(1));
    }

    @Test
    public void whenRemoveAbsentKeyValue() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertFalse(map.remove(1));
    }

    @Test
    public void whenHasNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 5);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenNoHasNext() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        map.put(2, 5);
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndModifiedModCountByPutHasNextGenException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> iterator = map.iterator();
        map.put(2, 5);
        iterator.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndModifiedModCountByRemoveHasNextGenException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        iterator.hasNext();
    }
}
