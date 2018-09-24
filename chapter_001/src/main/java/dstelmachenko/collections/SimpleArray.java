package dstelmachenko.collections;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) {
        if (index >= array.length) {
            throw new NoSuchElementException("Array out of bounds");
        }
        array[index++] = model;
    }

    public void set(int index, T model) {
        checkIndex(index);
        array[index++] = model;
    }

    public void delete(int index) {
        checkIndex(index);
        this.index--;
        for (int i = index; i < this.index; i++) {
            array[i] = array[i + 1];
        }
    }

    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    private void checkIndex(int index) {
        if (this.index < index) {
            throw new NoSuchElementException("Array out of bounds");
        }
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator<T> {
        int current = 0;
        public boolean hasNext() {
            return current < index;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Array out of bounds");
            }
            return (T) array[current++];
        }

        public void remove() {
            delete(--current);
        }

        public void replace(T model) {
            set(current, model);
        }
    }
}
