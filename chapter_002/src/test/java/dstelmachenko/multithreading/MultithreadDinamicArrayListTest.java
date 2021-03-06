package dstelmachenko.multithreading;

import dstelmachenko.collections.DinamicArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MultithreadDinamicArrayListTest {

    private MultithreadDinamicArrayList<Integer> arrayList;

    @Before
    public void beforeTest() {
        arrayList = new MultithreadDinamicArrayList<>(10);
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
        MultithreadDinamicArrayList<Integer> emptyArrayList = new MultithreadDinamicArrayList<>(10);
        assertThat(emptyArrayList.iterator().hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenCreatedanEmptyCollectionandGotNextElementsThenCollectionThrowException() {
        MultithreadDinamicArrayList<Integer> emptyArrayList = new MultithreadDinamicArrayList<>(10);
        emptyArrayList.iterator().next();
    }

    @Test
    public void whenDelteElementThenIteratorStillWorking() {
        Iterator<Integer> iter = arrayList.iterator();
        arrayList.delete(1);
        iter.next();
        assertThat(iter.next(), is(2));
    }

    @Test  (expected = NoSuchElementException.class)
    public void whenAddFourthElementThenIteratorStillHasThreeElemrnts() {
        Iterator<Integer> iter = arrayList.iterator();
        arrayList.add(4);
        iter.next();
        iter.next();
        iter.next();
        iter.next();
    }

    @Test
    public void whenDeleteSecondElementThenSecondElementWillBeThree() {
        arrayList.delete(1);
        assertThat(arrayList.get(1), is(3));

    }
}