package dstelmachenko.collections;

public class SimpleStack<T> {
    private DinamicLinkedList<T> list = new DinamicLinkedList<>();

    public T poll() {
        return list.deleteFirst();
    }

    public void push(T value) {
        list.addFirst(value);
    }

    public boolean isEmpty() {
        return list.getIndex() == 0;
    }

}