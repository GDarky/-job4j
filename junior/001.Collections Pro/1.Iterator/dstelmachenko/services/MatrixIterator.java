package dstelmachenko.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *@author DStelmachenko
 *@since 05.09.2018
 *
 */

public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int index = 0;
    private int wholeLength = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
        for (int[] arrayPart : values) {
            wholeLength += arrayPart.length;
        }
    }

    public boolean hasNext() {
        return wholeLength > index;
    }

    public Object next() {
        int indexI = values.length;
        int indexJ = 0;
        int result = -1;
        for (int i = 0, sum = 0; i < values.length; i++) {
            if (sum + values[i].length > index) {
                indexI = i;
                indexJ = index++ - sum;
                break;
            }
            sum += values[i].length;
        }
        if (indexI >= values.length) {
            throw new NoSuchElementException("Hasn't next element");
        }
        return values[indexI][indexJ];
    }

}
