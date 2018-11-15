package dstelmachenko.multithreading;


import dstelmachenko.collections.DinamicArrayList;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class MultithreadDinamicArrayList<E> {
    @GuardedBy("this")
    private final DinamicArrayList<E> list;

    public MultithreadDinamicArrayList(int size) {
        this.list = new DinamicArrayList<E>(size);
    }

    public synchronized void add(E model) {
        this.list.add(model);
    }

    public synchronized void set(int index, E model) {
        this.list.set(index, model);
    }

    public synchronized void delete(int index) {
        this.list.delete(index);
    }

    public synchronized E get(int index) {
        return (E) this.list.get(index);
    }

    public synchronized boolean isElementinList(E model) {
        return this.list.isElementinList(model);
    }

    public synchronized Iterator<E> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized DinamicArrayList<E> copy(DinamicArrayList<E> origList) {
        DinamicArrayList<E> copyList = new DinamicArrayList<>(origList.getIndex());
        Iterator<E> iterator = origList.iterator();
        while (iterator.hasNext()) {
            copyList.add(iterator.next());
        }
        return copyList;
    }


}
