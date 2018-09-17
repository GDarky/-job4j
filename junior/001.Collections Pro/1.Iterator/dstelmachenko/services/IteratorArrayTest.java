package dstelmachenko.services;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IteratorArrayTest {

    public static class ForEachArray implements Iterable {
        private final int[] values;

        public ForEachArray(final int[] values) {
            this.values = values;
        }

        public Iterator iterator() {
            return new IteratorArray(this.values);
        }
    }

    @Test
    public void whenhasNextPositionShouldReturnContantValue() {
        IteratorArray it = new IteratorArray(new int[] {1});
        it.next();
        it.hasNext();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }

    @Test
    public void whenGetNextThenGotnext() {
        IteratorArray it = new IteratorArray(new int[] {1, 3});
        it.next();
        int result = Integer.parseInt(it.next().toString());
        assertThat(result, is(3));
    }

    @Test
    public void foreach() {
        ForEachArray foreach = new ForEachArray(new int[] {1, 4, 5});
        for (Object value: foreach) {
            System.out.println(value);
        }
    }
}