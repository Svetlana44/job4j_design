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

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ hashCode >>> capacity;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int index(K key) {
        return key == null ? 0 : indexFor(hash(Objects.hashCode(key)));
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
        int hashKey = key == null ? 0 : key.hashCode();
        int hashMapKey = mapEntryKey == null ? 0 : mapEntryKey.hashCode();
        return hash(hashKey) == hash(hashMapKey) && mapEntryKey == key;
    }

    @Override
    public V get(K key) {
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                if (checkEqualKey(key, mapEntry.key)) {
                    return mapEntry.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (MapEntry<K, V> mapEntry : table) {
            if (mapEntry != null) {
                if (checkEqualKey(key, mapEntry.key)) {
                    table[index(key)] = null;
                    modCount++;
                    count--;
                    rsl = true;
                    break;
                }
            }
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