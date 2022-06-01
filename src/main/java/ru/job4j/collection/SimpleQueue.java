package ru.job4j.collection;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T first = out.pop();
        if (Objects.isNull(first)) {
            throw new NoSuchElementException();
        }
        return first;
    }

    public void push(T value) {
        T val;
        try {
            while ((val = out.pop()) != null) {
                in.push(val);
            }
        } catch (NoSuchElementException e) { }
        in.push(value);
        try {
            while ((val = in.pop()) != null) {
                out.push(val);
            }
        } catch (NoSuchElementException e) { }
    }
}
