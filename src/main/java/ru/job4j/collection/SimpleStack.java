package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        T[] values = (T[]) new Object[16];
        values[0] = value;
        int count = 1;
        for (T val : linked) {
            values[count++] = val;
            if (count > (0.75 * values.length)) {
                T[] newValues = (T[]) new Object[values.length * 2];
                System.arraycopy(values, 0, newValues, 0, count);
                values = newValues;
            }
        }
        linked = new ForwardLinked<>();
        for (int i = 0; i < count; i++) {
            linked.add(values[i]);
        }
    }
}
