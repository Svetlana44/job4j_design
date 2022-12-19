package ru.job4j.collection;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        if (table[index(key)] == null) {
            MapEntry<K, V> mapEntry = new MapEntry<>(key, value);
            table[index(key)] = mapEntry;
            modCount++;
            count++;
            rsl = true;
        }
        return rsl;
    }

    /*
    не надо было заменять число 16 в оригинальной формуле на capacity
     - емкость таблицы к сдвигу не имеет никакого отношения.
      Число 16 - это половина от размера int (32 байта)
     */
    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                temp[index(mapEntry.key)] = mapEntry;
            }
        }
        table = temp;
    }

    private boolean checkEqualKey(K key, K mapEntryKey) {
        int hashKey = Objects.hashCode(key);
        int hashMapKey = Objects.hashCode(mapEntryKey);
        return hash(hashKey) == hash(hashMapKey) && Objects.equals(mapEntryKey, key);
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = index(key);
        if (table[index] != null && checkEqualKey(key, table[index].key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = index(key);
        if (table[index] != null && checkEqualKey(key, table[index].key)) {
            table[index] = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;

            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value
                    + '}';
        }

    }

    @Override
    public String toString() {
        return "SimpleMap{" + Arrays.toString(table) + '}';
    }
}