package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;
    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteElementThenSecondWillBeOne() {
        list.delete();
        assertThat(list.get(1), is(1));
    }

    @Test
    public void whenDeleteElementThenSizeWillBeTwo() {
        list.delete();
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void whenDeleteAllElementThenListWillBeEmpty() {
        list.delete();
        list.delete();
        list.delete();
        assertThat(list.getSize(), is(0));
    }

    @Test
    public void whenDeleteFirstElementThenReturnOne() {
        list.delete();
        list.delete();
        assertThat(list.delete(), is(1));
    }

}