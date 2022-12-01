package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /*
    add last element
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        T rsl = head.value;
        head = head.next;
        node.next = null;
        node.value = null;
        return rsl;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public void deleteLast() {

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value
                    + ", next=" + next + '}';
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean revert() {
        /* impl reverts of linked list.*/
        boolean rsl = !(this.isEmpty() || this.head == null);
        if (rsl) {
            Node<T> previos = null;
            Node<T> temp = head;
            Node<T> next;

            while (temp != null) {
                next = temp.next;
                temp.next = previos;
                previos = temp;
                temp = next;
            }
            head = previos;
        }
        return rsl;
    }


    @Override
    public String toString() {
        return "ForwardLinked|" + "head=" + head + '|';
    }

    public static void main(String[] str) {
        ForwardLinked<Integer> fl = new ForwardLinked<>();
        fl.add(1);
        fl.add(2);
        fl.add(3);
        System.out.println(fl);
        fl.revert();
        System.out.println(fl);
    }
}