package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count > LOAD_FACTOR * capacity) {
            expand();
        }
        int index = (Objects.isNull(key)) ? 0 : indexFor(hash(key.hashCode()));
        boolean result = Objects.isNull(table[index]);
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        int index;
        MapEntry<K, V>[] table = new MapEntry[capacity];
        for (MapEntry<K, V> entry : this.table) {
            if (!Objects.isNull(entry)) {
                index = indexFor(hash(entry.key.hashCode()));
                table[index] = entry;
            }
        }
        this.table = table;
    }

    @Override
    public V get(K key) {
        int index = (Objects.isNull(key)) ? 0 : indexFor(hash(key.hashCode()));
        return table[index] == null ? null : (hash(table[index].key.hashCode()) == hash(key.hashCode()) && table[index].key.equals(key)) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = (Objects.isNull(key)) ? 0 : indexFor(hash(key.hashCode()));
        boolean result = !Objects.isNull(table[index]) && hash(table[index].key.hashCode()) == hash(key.hashCode()) && table[index].key.equals(key);
        if (result) {
            table[index] = null;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && Objects.isNull(table[index])) {
                    index++;
                }
                return index < capacity;
            }

            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
