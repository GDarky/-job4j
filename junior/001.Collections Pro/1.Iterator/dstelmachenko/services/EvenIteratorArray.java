package dstelmachenko.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIteratorArray implements Iterator {

    private final int[] numbers;
    private int index=0;

    public EvenIteratorArray(final int[] numbers) {
        this.numbers=numbers;
    }

    public boolean hasNext() {
        return (positionofNextElement()!=-1);
    }

    public Object next() {
        int nextElemPos = positionofNextElement();

        if (nextElemPos==-1)
            throw new NoSuchElementException("Hasn't next element");

        index = nextElemPos;
        return numbers[index++];
    }

    private int positionofNextElement() {
        int result=-1;
        for (int i=index; i<numbers.length; i++) {
            if (numbers[i] % 2 ==0) {
                result=i;
                break;
            }
        }
        return result;
    }
}
