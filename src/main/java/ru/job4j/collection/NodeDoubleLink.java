package ru.job4j.collection;

public class NodeDoubleLink<E> {
    E data;
    NodeDoubleLink<E> prev;
    NodeDoubleLink<E> next;

    public NodeDoubleLink(E data, NodeDoubleLink<E> prev, NodeDoubleLink<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{"
                + " data=" + data
                + '}';
    }
}
