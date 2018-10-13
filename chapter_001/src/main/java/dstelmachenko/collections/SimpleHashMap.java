package dstelmachenko.collections;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable {
    private int index;
    private int modCount;
    private Node<K, V>[] container;

    public SimpleHashMap() {
        container = new Node[100];
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        if (container[hash(key.hashCode())] == null) {
            Node<K, V> node = new Node<>(key, value);
            checkCapacity();
            container[hash(key.hashCode())] = node;
            index++;
            modCount++;
            result = true;
        }
        return result;
    }

    public V get(K key) {
        return (container[hash(key.hashCode())] != null) ? container[hash(key.hashCode())].getValue() : null;
    }

    public boolean delete(K key) {
        boolean result = false;
        if (container[hash(key.hashCode())] != null) {
            container[hash(key.hashCode())] = null;
            index--;
            modCount++;
            result = true;
        }
        return result;
    }

    private void checkCapacity() {
        if (index >= container.length) {
            int oldCapacity = container.length;
            int newCapacity = oldCapacity * 2;
            if  (newCapacity > Integer.MAX_VALUE - 8) {
                throw new OutOfMemoryError();
            }
            container = Arrays.copyOf(container, newCapacity);
        }
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((key.hashCode()) ^ (key.hashCode() >>> 16)) & (container.length - 1);
    }

    private class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new SimpleHashMap.ArrayIterator();
    }

    private class ArrayIterator implements Iterator<K> {
        private int current;
        private int next;
        private int expectedModCount = SimpleHashMap.this.modCount;

        @Override
        public boolean hasNext() {
            boolean result = false;
            if (current < container.length - 1) {
                for (int i = current + 1; i < container.length; i++) {
                    if (container[i] != null) {
                        next = i;
                        result = true;
                        break;
                    }
                }
            }
            return result;
        }

        @Override
        public K next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException("Array out of bounds");
            }
            current = next;
            return (K) container[current].getKey();
        }

        private final void checkForComodification() {
            if (expectedModCount != SimpleHashMap.this.modCount) {
                throw new ConcurrentModificationException("");
            }
        }
    }
}
