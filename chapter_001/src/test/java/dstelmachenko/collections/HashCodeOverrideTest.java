package dstelmachenko.collections;

import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * В первом тесте значения полей объектов не равны и у них разные хэши
 * Во втором случае в памяти разные объекты, но с полным набором одинаковых полей
 * и хэши у них равны.
 */
public class HashCodeOverrideTest {
    private HashCodeOverride worker1;
    private HashCodeOverride worker2;
    private HashCodeOverride worker3;

    @Before
    public void beforeTest() {
        worker1 = new HashCodeOverride("Frank", "Whilliams",
                new GregorianCalendar(1963, 11, 3), true, 45761.23, (float) 381.22,
                393523523);
        worker1.addChild("Rose", "Whilliams",
                new GregorianCalendar(1995, 3, 14), false);
        worker1.addChild("Phillip", "Whilliams",
                new GregorianCalendar(1999, 7, 30), true);
        worker2 = new HashCodeOverride("Mary", "Whilliams",
                new GregorianCalendar(1968, 5, 2), true, 41465.37, (float) 298.48,
                393523523);
        worker2.addChild("Rose", "Whilliams",
                new GregorianCalendar(1995, 3, 14), false);
        worker2.addChild("Phillip", "Whilliams",
                new GregorianCalendar(1999, 7, 30), true);
        worker3 = new HashCodeOverride("Frank", "Whilliams",
                new GregorianCalendar(1963, 11, 3), true, 45761.23, (float) 381.22,
                393523523);
        worker3.addChild("Rose", "Whilliams",
                new GregorianCalendar(1995, 3, 14), false);
        worker3.addChild("Phillip", "Whilliams",
                new GregorianCalendar(1999, 7, 30), true);
    }

    @Test
    public void whenDiffernetWorkersThenGotDifferentHashes() {
        assertThat(worker1.hashCode(), IsNot.not(worker2.hashCode()));
    }

    @Test
    public void whenObjectsHaveSamePropertiesThenGotSameHashes() {
        assertThat(worker1.hashCode(), is(worker3.hashCode()));
    }

}