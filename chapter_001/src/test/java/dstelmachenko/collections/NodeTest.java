package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NodeTest {
    Node<Integer> first;
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);

    @Before
    public void beforeTest() {
      first = node1;
      node1.setNext(node2);
      node2.setNext(node3);
      node3.setNext(node4);
    }

    @Test
    public void thenNotCicledThenReturnFalse() {
        node4.setNext(node5);
        assertThat(first.hasCycle(first), is(false));
    }

    @Test
    public void thenCicledinMiddleThenReturnTrue() {
        node4.setNext(node2);
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void thenCicledinEndThenReturnTrue() {
        node4.setNext(node5);
        node5.setNext(node5);
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void thenCicledinBeginningThenReturnTrue() {
        node1.setNext(node1);
        assertThat(first.hasCycle(first), is(true));
    }

    @Test
    public void thenRoundCicledThenReturnTrue() {
        node4.setNext(node5);
        node5.setNext(node1);
        assertThat(first.hasCycle(first), is(true));
    }

}