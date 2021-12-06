package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private final int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        int index = checNull(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
            if ((float) count / table.length >= LOAD_FACTOR) {
                expand();
            }
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash(hash);
    }

    private void expand() {
        MapEntry<K, V>[] tempTable = table;
        table = new MapEntry[tempTable.length * 10];
        for (MapEntry<K, V> mapEntry : tempTable) {
            if (mapEntry != null) {
                put(mapEntry.key, mapEntry.value);
            }
        }
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = checNull(key);
        MapEntry<K, V> mapEntry = table[index];
        if (mapEntry != null && (mapEntry.key == null || mapEntry.key.equals(key))) {
            result = mapEntry.value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = checNull(key);
        if (table[index] != null && (table[index].key == null || table[index].key.equals(key))) {
            table[index] = null;
            modCount++;
            count--;
            result = true;
        }
        return result;
    }

    private int checNull(K key) {
        int hashCode = key == null ? 0 : key.hashCode();
        return indexFor(hashCode);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
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

    }

}