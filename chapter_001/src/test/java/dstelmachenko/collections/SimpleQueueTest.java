package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
        queue.push(8);
        queue.push(3);
        queue.push(6);
    }

    @Test
    public void whenPollOneTimeThenGotEight() {
        assertThat(queue.poll(), is(8));
    }

    @Test
    public void whenPollThreeTimesThenGotSix() {
        queue.poll();
        queue.poll();
        assertThat(queue.poll(), is(6));
    }

    @Test
    public void whenPollOneTimeAndThenPushANumberAndAfterThapPollTwoTimesThenGotSix() {
        queue.poll();
        queue.push(4);
        queue.poll();
        assertThat(queue.poll(), is(6));
    }

    @Test
    public void whenPollForTimesThenGotNull() {
        queue.poll();
        queue.poll();
        queue.poll();
        assertThat(queue.poll(), is(nullValue()));
    }

    @Test
    public void whenPollAfterPushSixteenAndAfterThatPollAfterPushFortyFreeThenGotFortyFree() {
        queue.poll();
        queue.poll();
        queue.poll();
        queue.push(16);
        queue.poll();
        queue.push(43);
        assertThat(queue.poll(), is(43));
    }
}