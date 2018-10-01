package dstelmachenko.collections;

import java.util.*;

public class DinamicArrayList<T> implements Iterable {
    private Object[] container;
    private int index;
    private transient int modCount;

    public DinamicArrayList(int size) {
        this.container = new Object[size];
    }

    public void add(T model) {
        checkCapacity();
        container[index++] = model;
        modCount++;
    }

    public void set(int index, T model) {
        checkIndex(index);
        container[index] = model;
    }

    public void delete(int index) {
        checkIndex(index);
        this.index--;
            for (int i = index; i < this.index; i++) {
                container[i] = container[i + 1];
            }
        container[this.index] = null;
        modCount++;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) container[index];
    }

    public boolean isElementinList(T model) {
        boolean result = false;
        for (int i = 0; i < index; i++) {
            if (container[i].equals(model)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void checkIndex(int index) {
        if (index < 0 || this.index <= index) {
            throw new NoSuchElementException("Array out of bounds");
        }
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

    @Override
    public Iterator<T> iterator() {
            return new DinamicArrayList.ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int current;
        private int expectedModCount = DinamicArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return current < index;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException("Array out of bounds");
            }
            return (T) container[current++];
        }

        @Override
        public void remove() {
            checkForComodification();
            delete(--current);
        }

        public void replace(T model) {
            set(current, model);
        }

        private final void checkForComodification() {
            if (expectedModCount != DinamicArrayList.this.modCount) {
                throw new ConcurrentModificationException("");
            }
        }
    }
}
