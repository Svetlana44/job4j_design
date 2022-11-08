package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedListDoubleLink<E> implements LinkedList<E> {
    NodeDoubleLink<E> firstNode = new NodeDoubleLink<>(null, null, null);
    NodeDoubleLink<E> lastNode = new NodeDoubleLink<>(null, null, null);

    NodeDoubleLink<E> currentNode;
    List<NodeDoubleLink<E>> list = new ArrayList<>();

    private int size = 0;
    private int modCount;

    public SimpleLinkedListDoubleLink() {
        firstNode.next = lastNode;
        lastNode.prev = firstNode;
    }

    @Override

    public void add(E value) {
        NodeDoubleLink<E> additionalNode = lastNode;
        additionalNode.data = value;
        lastNode = new NodeDoubleLink<>(null, additionalNode, null);
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
                NodeDoubleLink<E> rsl = list.get(pointer++);
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