package dstelmachenko.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIteratorArray implements Iterator {

    private final int[] numbers;
    private int index = 0;
    private int nextElementIndex = 0;

    public EvenIteratorArray(final int[] numbers) {
        this.numbers = numbers;
    }

    public boolean hasNext() {
        boolean result = false;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                nextElementIndex = i;
                result = true;
                break;
            }
        }
        return result;
    }

    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Hasn't next element");
        }
        index = nextElementIndex;
        return numbers[index++];
    }

}
