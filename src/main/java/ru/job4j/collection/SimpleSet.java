package ru.job4j.collection;

/*import ru.job4j.collection.SimpleArrayList;*/

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    public SimpleArrayList<T> getSet() {
        return set;
    }

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!this.contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        Iterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), value)) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(set, simpleSet.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    @Override
    public String toString() {
        Iterator<T> iterator = set.iterator();
        String rsl = "SimpleSet:";
        while (iterator.hasNext()) {
            rsl += iterator.next();
        }

        return rsl;
    }
}
