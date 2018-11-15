package dstelmachenko.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private int maxCapacity;

    public SimpleBlockingQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (queue.size() == maxCapacity) {
                wait();
            }
            queue.offer(value);
            notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (queue.isEmpty()) {
                wait();
            }
            T value = queue.poll();
            notify();
            return value;
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
