package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index = 0;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {

        while (index < data.length - 1 && !(Optional.ofNullable(data[index]).isPresent())) {
            index++;
        }
        return index < data.length && Optional.ofNullable(data[index]).isPresent();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
