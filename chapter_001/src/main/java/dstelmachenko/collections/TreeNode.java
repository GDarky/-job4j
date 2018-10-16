package dstelmachenko.collections;

import java.util.ArrayList;
import java.util.List;

public class    TreeNode<E extends Comparable<E>> {
    private final List<TreeNode<E>> children = new ArrayList<>();
    private final E value;

    public TreeNode(final E value) {
        this.value = value;
    }

    public void add(TreeNode<E> child) {
        this.children.add(child);
    }

    public List<TreeNode<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return value;
    }
}
