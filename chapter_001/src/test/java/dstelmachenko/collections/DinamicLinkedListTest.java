package dstelmachenko.collections;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
}