package dstelmachenko.collections;

public class SimpleQueue<T> {
    private SimpleStack<T> left = new  SimpleStack<>();
    private SimpleStack<T> right = new  SimpleStack<>();
    private int index = 0;

    public T poll() {
        if (right.isEmpty()) {
            while (!left.isEmpty()) {
                right.push(left.poll());
            }
        }
        T result = null;
        if (!right.isEmpty()) {
            result = right.poll();
            index--;
        }
        return result;
    }

    public void push(T value) {
        left.push(value);
        index++;
    }
}
