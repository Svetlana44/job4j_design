package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        size++;
        if (size >= container.length) {
            container = Arrays.copyOf(container, size * 2);
        }
        container[size - 1] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T temp = container[index];
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        size--;
        modCount++;
        T temp = container[index];
        System.arraycopy(
                container, /*откуда копируем*/
                index + 1, /*начиная с какого индекса*/
                container, /* куда копируем*/
                index, /* начиная с какого индекса*/
                container.length - index - 1 /* сколько элементов копируем*/
        );
        // на последнее место ставим null, чтобы не было утечки памяти (если удаляем последний элемент)
        container[container.length - 1] = null;
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
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(point++);
            }

        };
    }

    public static void main(String[] str) {
        SimpleList<Integer> simpleList = new SimpleArrayList(3);
        simpleList.add(1);
        simpleList.add(2);
        simpleList.add(3);

        System.out.println(simpleList.size());
        System.out.println(simpleList.remove(1));
    }
}