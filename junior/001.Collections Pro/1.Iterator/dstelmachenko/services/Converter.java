package dstelmachenko.services;

import java.util.Iterator;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new IteratorofIterators(it);
    }
}
