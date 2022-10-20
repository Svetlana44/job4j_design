package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return -1 < point;
    }

    @Override
    public Integer next() {
        if (data.length == 0) {
            throw new NoSuchElementException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}