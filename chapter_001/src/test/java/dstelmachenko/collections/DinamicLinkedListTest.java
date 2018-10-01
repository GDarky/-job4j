package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class DinamicLinkedListTest {

    private DinamicLinkedList<Integer> linkedList;

    @Before
    public void beforeTest() {
        linkedList = new DinamicLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
    }

    @Test
    public void whenAdd4Then4WasAdded() {
        linkedList.add(4);
        Integer number = linkedList.get(3);
        assertThat(number, is(4));
    }

    @Test
    public void whenAdd9FirstThen9WasAddedToHead() {
        linkedList.addFirst(9);
        Integer number = linkedList.get(0);
        assertThat(number, is(9));
    }

    @Test
    public void whenSetsevenToSecondElementThenIsSevenInSecobdElement() {
        linkedList.set(1, 7);
        assertThat(linkedList.get(0), is(1));
        assertThat(linkedList.get(1), is(7));
        assertThat(linkedList.get(2), is(3));
    }

    @Test
    public void whenGotFirstElementThenItIsOne() {
        assertThat(linkedList.get(0), is(1));
    }

    @Test
    public void whenCreatedanEmptyCollectionThenCollectionhasntNextElement() {
        DinamicLinkedList<Integer> emptyLinkedList = new DinamicLinkedList<>();
        assertThat(emptyLinkedList.iterator().hasNext(), is(false));
    }

    @Test
    public void whenCreatedanEmptyCollectionandGotNextElementsThenCollectionThrowException() {
        DinamicLinkedList<Integer> emptyLinkedList = new DinamicLinkedList<>();
        try {
            emptyLinkedList.iterator().next();
            fail("Array out of bounds");
        } catch (NoSuchElementException ex) {
            assertThat(ex.getMessage(), containsString("Array out of bounds"));
        }

    }

    @Test
    public void whenDelteElementThenIteratorThrowsExeption() {
        Iterator<Integer> iter = linkedList.iterator();
        linkedList.deleteFirst();
        try {
            iter.next();
        } catch (ConcurrentModificationException ex) {
            assertThat(ex.getMessage(), containsString(""));
        }
    }

    @Test
    public void whenAddElementThenIteratorThrowsExeption() {
        Iterator<Integer> iter = linkedList.iterator();
        linkedList.add(4);
        try {
            iter.next();
        } catch (ConcurrentModificationException ex) {
            assertThat(ex.getMessage(), containsString(""));
        }
    }

    @Test
    public void whenDeleteFirstElementThenFirstElementWillBeTwo() {
        linkedList.deleteFirst();
        assertThat(linkedList.get(0), is(2));

    }
}