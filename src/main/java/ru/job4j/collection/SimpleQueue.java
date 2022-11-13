package ru.job4j.collection;
/*
Метод poll() - должен возвращать первое значение и удалять его из коллекции.
Метод push(T value) - помещает значение в конец.
Алгоритм.

Данные очереди нужно хранить в ru.job4j.collection.SimpleStack. Для этого задания нужны два стека.
Представьте, что у вас стопка с тарелками. Вам нужно достать нижнюю тарелку. Для этого вы перекладываете все тарелки в другую стопку.
Стопка - это стек. Для операции извлечения первой тарелки нужны две стопки.
Генерацию исключения NoSuchElementException при попытке достать элемент из пустой очереди сделайте в методе poll().
 */

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size = 0;

    public T poll() {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        size--;
        return in.pop();
    }

    public void push(T value) {
        in.addLast(value);
        out.addFirstF(value);
        size++;
    }

    @Override
    public String toString() {
        return "SimpleQueue{" + "in=" + in
                + ", out=" + out
                + ", size=" + size + '}';
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> queue;
        queue = new SimpleQueue<>();
        System.out.println(queue);
        queue.push(1);
        System.out.println(queue);
        queue.push(2);
        System.out.println(System.lineSeparator() + queue);
        System.out.println(queue.poll());
        System.out.println(queue);

    }
}