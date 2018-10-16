package dstelmachenko.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleUserTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenCheckFithElementInIteratorThenGetFive() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        Iterator<Integer> iter = tree.iterator();
        for (int i = 0; i < 4; i++) {
            iter.next();
        }
        assertThat(iter.next(), is(5));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddNewElementThenIteratorThrowsExeption() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        Iterator<Integer> iter = tree.iterator();
        tree.add(1, 4);
        iter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreatedCollectionandAddTwoElementsGotNextElementsTreeTimesThenCollectionThrowException() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        Iterator<Integer> iter = tree.iterator();
        iter.next();
        iter.next();
        iter.next();
    }

    @Test
    public void whenCheckIsBinaryNotBinaryTreeThenGetFalse() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenCheckIsBinaryBinaryTreeThenGetTrue() {
        SimpleUserTree<Integer> tree = new SimpleUserTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(4, 5);
        tree.add(4, 7);
        tree.add(5, 6);
        assertThat(tree.isBinary(), is(true));
    }
}