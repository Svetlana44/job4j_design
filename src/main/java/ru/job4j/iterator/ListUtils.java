/*
- addAfter() вставляет после индекса;
- addBefore() вставляет до индекса;
- removeIf() удаляет все элементы, которые удовлетворяют предикату.
Запрещено использовать метод List.removeIf;
- replaceIf() заменяет все элементы, которые удовлетворяют предикату;
- removeAll() удаляет из списка те элементы, которые есть в elements.
Запрещено использовать метод List.removeAll().
Запрещено использовать методы List.removeIf(),List.removeAll()
 */

package ru.job4j.iterator;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        iterator.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> listIterator = list.listIterator();
        for (int i = 0; i < index + 1; i++) {
            listIterator.next();
        }
        listIterator.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (filter.test(listIterator.next())) {
                listIterator.remove();
                listIterator.add(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> listIterator = list.listIterator();
        for (T el : elements) {
            removeIf(list, n -> n == el);
        }
    }
}