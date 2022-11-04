package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void multiplayCapacity() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else {
            container = Arrays.copyOf(container, size * 2);
        }
    }

    @Override
    public void add(T value) {
        if (size >= container.length) {
            multiplayCapacity();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T temp = get(index);
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        T temp = get(index);
        System.arraycopy(
                container, /*откуда копируем*/
                index + 1, /*начиная с какого индекса*/
                container, /* куда копируем*/
                index, /* начиная с какого индекса*/
                container.length - index - 1 /* сколько элементов копируем*/
        );
        /* на последнее место ставим null, чтобы не было утечки памяти (если удаляем последний элемент)*/
        container[container.length - 1] = null;
        size--;
        modCount++;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }

        };
    }
}