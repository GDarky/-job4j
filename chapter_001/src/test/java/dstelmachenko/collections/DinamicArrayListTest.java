package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

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
    public void whenSetsevenToSecondElementThenIsSevenInSecobdElement() {
        arrayList.set(1, 7);
        assertThat(arrayList.get(0), is(1));
        assertThat(arrayList.get(1), is(7));
        assertThat(arrayList.get(2), is(3));
    }

    @Test
    public void whenGotFirstElementThenItIsOne() {
        assertThat(arrayList.get(0), is(1));
    }

}