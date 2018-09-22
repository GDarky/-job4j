package dstelmachenko.collections;

import dstelmachenko.collections.Base;

public interface Store<R extends Base> {
    void add(R model);

    boolean replace(String id, R model);

    boolean delete(String id);

    R findById(String id);
}
