package dstelmachenko.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
*@author DStelmachenko
*@since 05.09.2018
*
 */

public class IteratorArray implements Iterator {

    private final int[] values;
    private int index = 0;

    public IteratorArray(final int[] values) {
        this.values = values;
    }

    public boolean hasNext() {
        return values.length > index;
    }

    @Override
    public String toString() {
        return String.valueOf(values[index++]);
    }

    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Array out of bounds");
        }
        return values[index++];
    }
}
