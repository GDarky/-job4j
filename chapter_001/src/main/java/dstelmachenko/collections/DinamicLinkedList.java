package dstelmachenko.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DinamicLinkedList<T> implements Iterable {
    transient int index = 0;
    transient Node<T> first;
    transient Node<T> last;
    private transient int modCount = 0;

    public void add(T model) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, model, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        index++;
        modCount++;
    }

    public int getIndex() {
        return index;
    }

    public void addFirst(T model) {
        final Node<T> l = first;
        final Node<T> newNode = new Node<>(null, model, first);
        first = newNode;
        if (l == null) {
            last = newNode;
        } else {
            l.prev = newNode;
        }
        index++;
        modCount++;
    }

    public T deleteFirst() {
        if (first == null) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds!");
        }
        Node<T> l = first;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
            index--;
            modCount++;
        } else {
            first = null;
            last = null;
            index--;
            modCount++;
        }
        return l.item;
    }

    public void set(int index, T model) {
        checkIndex(index);
        Node<T> x = node(index);
        x.item = model;
    }

    public T get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    private void checkIndex(int index) {
        if (index < 0 || this.index <= index) {
            throw new NoSuchElementException("Out of bounds");
        }
    }

    private Node<T> node(int index) {
        if (index < (this.index >> 1)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = last;
            for (int i = this.index - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkListIterator();
    }

    class LinkListIterator implements Iterator<T> {
        private Node<T> next;
        private Node<T> currentNode;
        int current = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return current < index;
        }

        @Override
        public T next() {
            checkForComodification();
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException("Array out of bounds");
            }
            currentNode = next;
            next = next.next;
            current++;
            return currentNode.item;
        }

        public void replace(T model) {
            checkForComodification();
            currentNode.item = model;
        }

        final void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("");
            }
        }
    }
}
