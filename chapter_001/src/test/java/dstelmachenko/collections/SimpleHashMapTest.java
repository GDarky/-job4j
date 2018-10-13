package dstelmachenko.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.util.Objects.isNull;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    private SimpleHashMap<String, Integer> map;

    @Before
    public void beforeTest() {
        map = new SimpleHashMap<>();
        map.insert("User 1", 1);
        map.insert("User 3", 3);
        map.insert("User 2", 2);
        map.insert("Access Rights", 562);
        map.insert("User Role 120", 65553);
    }

    @Test
    public void whenAddUserRole43ThenUserRole43WasAdded() {
        if (map.insert("User Role 43", 6543)) {
            Integer number = map.get("User Role 43");
            assertThat(number, is(6543));
        } else {
            assertThat(map.get("User Role 43"), is(nullValue()));
        }
    }

    @Test
    public void whenDeleteUser2ThenUser2NotExists() {
        map.delete("User 2");
        assertThat(map.get("User2"), is(nullValue()));
    }

    @Test
    public void whenGotKeyUserRole120KeyThenReturned65553Value() {
        Integer number = map.get("User Role 120");
        assertThat(number, is(65553));
    }

    @Test
    public void whenCreatedanEmptyCollectionThenCollectionhasntNextElement() {
        SimpleHashMap<String, Integer> emptyMap = new SimpleHashMap<>();
        assertThat(emptyMap.iterator().hasNext(), is(false));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenCreatedanEmptyCollectionandGotNextElementsThenCollectionThrowException() {
        SimpleHashMap<String, Integer> emptyMap = new SimpleHashMap<>();
        emptyMap.iterator().next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenDelteElementThenIteratorThrowsExeption() {
        Iterator<String> iter = map.iterator();
        map.delete("User 1");
        iter.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenAddElementThenIteratorThrowsExeption() {
        Iterator<String> iter = map.iterator();
        boolean result = false;
        Integer i = 10;
        while (!result) {
            result = map.insert("User " + (i++).toString(), 2);
        }
        iter.next();
    }

    @Test
    public void whenDeleteSecondElementThenSecondElementWillBeUser1() {
        map.delete("User Role 120");
        Iterator<String> iter = map.iterator();
        assertThat(iter.next(), is("User 1"));
    }

}