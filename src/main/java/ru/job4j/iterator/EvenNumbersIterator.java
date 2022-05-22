package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;
    private boolean flagCanRemoveElement = false;
    private int indexCanRemoveElement;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index != data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        flagCanRemoveElement = true;
        indexCanRemoveElement = index;
        return data[index++];
    }

    @Override
    public void remove() {
        removeElementAndShiftIndex();
        flagCanRemoveElement = false;
    }

    private void removeElementAndShiftIndex() {
        if (!flagCanRemoveElement) {
            throw new IllegalStateException();
        }
        int[] newData = new int[data.length - 1];
        System.arraycopy(data, 0, newData, 0, indexCanRemoveElement);
        System.arraycopy(data, indexCanRemoveElement + 1, newData, indexCanRemoveElement, data.length - indexCanRemoveElement - 1);
        data = newData;
        index--;
    }
}