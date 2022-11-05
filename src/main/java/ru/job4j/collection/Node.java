package ru.job4j.collection;

public class Node<E> {
    E data;
    Node<E> prev;
    Node<E> next;

    public Node(E data, Node<E> prev, Node<E> next) {
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
