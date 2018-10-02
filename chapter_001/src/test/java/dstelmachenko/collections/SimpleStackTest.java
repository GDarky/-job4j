package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();
        stack.push(8);
        stack.push(3);
        stack.push(6);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenPoolLastThenGot6poll() {
        assertThat(stack.poll(), is(6));
    }

    @Test
    public void whenPollAllThenGot8poll() {
        stack.poll();
        stack.poll();
        assertThat(stack.poll(), is(8));
    }

    @Test
    public void whenPollAllAndOneThenGotNull() {
        stack.poll();
        stack.poll();
        stack.poll();
        try {
            stack.poll();
            fail("Out of bounds!");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertThat(ex.getMessage(), containsString("Out of bounds!"));
        }
    }

    @Test
    public void whenPush16ThenPoll16() {
        stack.push(16);
        assertThat(stack.poll(), is(16));
    }

    @Test
    public void whenStackEmptyisEmptyReturnsTrue() {
        stack.poll();
        stack.poll();
        stack.poll();
        assertThat(stack.isEmpty(), is(true));
    }
}