package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    Node<E> firstNode = new Node<>(null, null, null);
    Node<E> lastNode = new Node<>(null, null, null);

    Node<E> currentNode;
    List<Node<E>> list = new ArrayList<>();

    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
    }

    @Override

    public void add(E value) {
        Node<E> additionalNode = lastNode;
        additionalNode.data = value;
        lastNode = new Node<>(null, additionalNode, null);
        additionalNode.next = lastNode;
        currentNode = additionalNode;
        size++;
        modCount++;
        list.add(additionalNode);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        currentNode = firstNode.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = modCount;
            int pointer = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return pointer < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> rsl = list.get(pointer++);
                return rsl.data;
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleLinkedList{"
                + "firstNode=" + firstNode
                + currentNode
                + ", lastNode=" + lastNode
                + "size" + size
                + '}';
    }
}