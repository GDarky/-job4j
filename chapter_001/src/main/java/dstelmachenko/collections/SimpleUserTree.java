package dstelmachenko.collections;

import java.util.*;

public class SimpleUserTree<E extends Comparable<E>> implements SimpleTree<E> {
    private TreeNode<E> root;
    private transient int modCount = 0;

    public SimpleUserTree(E root) {
        this.root = new TreeNode<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<TreeNode<E>> currentParent;
        TreeNode<E> currentChild;
        currentParent = this.findBy(parent);
        currentChild = new TreeNode(child);
        currentParent.get().add(currentChild);
        modCount++;
        return false;
    }

    @Override
    public Optional<TreeNode<E>> findBy(E value) {
        Optional<TreeNode<E>> rsl = Optional.empty();
        Queue<TreeNode<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            TreeNode<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (TreeNode<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator iterator() {
        return new SimpleUserTree.TreeIterator();
    }

    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {
        Queue<TreeNode<E>> elementsTmp = new LinkedList<>();
        Queue<TreeNode<E>> elements = new LinkedList<>();
        int expectedModCount = modCount;

        public TreeIterator() {
            elementsTmp.offer((TreeNode<E>) root);
            while (!elementsTmp.isEmpty()) {
                TreeNode<E> el = elementsTmp.poll();
                elements.offer(el);
                for (TreeNode<E> child : el.leaves()) {
                    elementsTmp.offer(child);
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !elements.isEmpty();
        }

        @Override
        public E next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            TreeNode<E> el = elements.poll();
            return el.getValue();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        final void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("");
            }
        }
    }
}
