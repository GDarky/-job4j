package dstelmachenko.collections;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {
    private SimpleSet<String> set = new SimpleSet<>();

    @Test
    public void whenAddedElementThenElementinSet() {
        String str = "";
        set.add("String 1");
        for (Object s : set) {
            str = (String) s;
        }
        assertThat(str, is("String 1"));
    }

    @Test
    public void whenTriedtoAddSameElementThenSetnotChanged() {
        String str = "";
        int strString2Count = 0;
        set.add("String 1");
        set.add("String 2");
        set.add("String 3");
        set.add("String 4");
        set.add("String 2");
        set.add("String 2");
        for (Object s : set) {
            str = (String) s;
            if (str.equals("String 2")) {
                strString2Count++;
            }
        }
        assertThat(strString2Count, is(1));
    }
}