package dstelmachenko.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorofIterators implements Iterator {

    private final Iterator<Iterator<Integer>> iter;
    private Iterator<Integer> currIter = null;

    public IteratorofIterators(Iterator<Iterator<Integer>> iterators) {
        this.iter = iterators;
    }

    public boolean hasNext() {
        getNextIter();
        return (currIter != null && currIter.hasNext());
    }

    public Object next() {
        getNextIter();
        if (currIter == null) {
            throw new NoSuchElementException("Hasn't next element");
        }
        return currIter.next();
    }

    private void getNextIter() {
        if (currIter != null && currIter.hasNext()) {
            return;
        }
        currIter = null;
        while (iter.hasNext()) {
            Iterator<Integer> currentIter = iter.next();
            if (currentIter.hasNext()) {
                currIter = currentIter;
                break;
            }
        }
    }
}
