package ru.job4j.collection;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public void addLast(T value) {

        linked.add(value);
    }

    public void addFirstF(T value) {
        linked.addFirst(value);
    }

    @Override
    public String toString() {
        return "SimpleStack{" + linked + '}';
    }
}