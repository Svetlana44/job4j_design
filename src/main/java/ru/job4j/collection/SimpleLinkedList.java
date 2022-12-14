package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    Node<E> firstNode = new Node<>(null, null);
    Node<E> lastNode = new Node<>(null, null);

    Node<E> currentNode;

    private int size = 0;
    private int modCount;

    public SimpleLinkedList() {
        firstNode.next = lastNode;
    }

    @Override

    public void add(E value) {
        Node<E> additionalNode = lastNode;
        additionalNode.data = value;
        lastNode = new Node<>(null, null);
        additionalNode.next = lastNode;
        currentNode = additionalNode;
        size++;
        modCount++;
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
            Node<E> tempNode = firstNode;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return tempNode.next != null && tempNode.next != lastNode && size != 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = tempNode.next.data;
                tempNode = tempNode.next;
                return rsl;
            }
        };
    }

    @Override
    public String toString() {
        return "SimpleLinkedList{"
                + "firstNode=" + firstNode
                + "size" + size
                + '}';
    }

    public static void main(String[] args) {
        LinkedList<Integer> list;
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);
    }
}