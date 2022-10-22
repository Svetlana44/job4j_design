package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            while (column < data[row].length) {

                if (!(Objects.equals(data[row][column], null))) {
                    return true;
                }
                column++;
            }
            row++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int r = row;
        int c = column;
        if (column == data[row].length - 1) {
            column = 0;
            row++;
        } else {
            column++;
        }
        return data[r][c];
    }
}