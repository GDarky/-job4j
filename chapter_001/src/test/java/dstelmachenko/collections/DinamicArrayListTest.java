package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DinamicArrayListTest {

    private DinamicArrayList<Integer> arrayList;

    @Before
    public void beforeTest() {
        arrayList = new DinamicArrayList<>(10);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
    }

    @Test
    public void whenAdd4Then4WasAdded() {
        arrayList.add(4);
        Integer number = arrayList.get(3);
        assertThat(number, is(4));
    }

    @Test
    public void whenSetSevenToSecondElementThenIsSevenInSecondElement() {
        arrayList.set(1, 7);
        assertThat(arrayList.get(0), is(1));
        assertThat(arrayList.get(1), is(7));
        assertThat(arrayList.get(2), is(3));
    }

    @Test
    public void whenGotFirstElementThenItIsOne() {
        assertThat(arrayList.get(0), is(1));
    }

    @Test
    public void whenCreatedanEmptyCollectionThenCollectionhasntNextElement() {
        DinamicArrayList<Integer> emptyArrayList = new DinamicArrayList<>(10);
        assertThat(emptyArrayList.iterator().hasNext(), is(false));
    }

    @Test
    public void whenCreatedanEmptyCollectionandGotNextElementsThenCollectionThrowException() {
        DinamicArrayList<Integer> emptyArrayList = new DinamicArrayList<>(10);
        try {
            emptyArrayList.iterator().next();
            fail("Array out of bounds");
        } catch (NoSuchElementException ex) {
            assertThat(ex.getMessage(), containsString("Array out of bounds"));
        }

    }

    @Test
    public void whenDelteElementThenIteratorThrowsExeption() {
        Iterator<Integer> iter = arrayList.iterator();
        arrayList.delete(1);
        try {
            iter.next();
        } catch (ConcurrentModificationException ex) {
            assertThat(ex.getMessage(), containsString(""));
        }
    }

    @Test
    public void whenAddElementThenIteratorThrowsExeption() {
        Iterator<Integer> iter = arrayList.iterator();
        arrayList.add(4);
        try {
            iter.next();
        } catch (ConcurrentModificationException ex) {
            assertThat(ex.getMessage(), containsString(""));
        }
    }

    @Test
    public void whenDeleteSecondElementThenSecondElementWillBeThree() {
        arrayList.delete(1);
        assertThat(arrayList.get(1), is(3));

    }
}