package dstelmachenko.collections;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable {
    private DinamicArrayList<E> array = new DinamicArrayList<E>(10);
    private int modCount = 0;

    public void add(E e) {
        if (!array.isElementinList(e)) {
            array.add(e);
        }
    }

    @Override
    public Iterator iterator() {
        return array.iterator();
    }
}
