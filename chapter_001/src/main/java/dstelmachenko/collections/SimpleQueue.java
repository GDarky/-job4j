package dstelmachenko.collections;

public class SimpleQueue<T> {
    private SimpleStack<T> stack1 = new  SimpleStack<>();
    private SimpleStack<T> stack2 = new  SimpleStack<>();
    private int index = 0;

    public T poll() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.poll());
            }
        }
        T result = null;
        if (!stack2.isEmpty()) {
            result = stack2.poll();
            index--;
        }
        return result;
    }

    public void push(T value) {
        stack1.push(value);
        index++;
    }
}
