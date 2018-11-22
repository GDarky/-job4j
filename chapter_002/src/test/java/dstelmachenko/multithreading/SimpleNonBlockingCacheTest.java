package dstelmachenko.multithreading;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleNonBlockingCacheTest {
    @Test
    public void whenUpdaingSimultaneouslyThenThrowException() throws InterruptedException {

        final SimpleNonBlockingCache cache = new SimpleNonBlockingCache();
        cache.add(new Base(1, "task1"));
        cache.add(new Base(2, "task2"));
        cache.add(new Base(3, "task3"));

        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        Base newBase3 = new Base(cache.getBasebyId(3));
                        newBase3.setName("new task3");
                        Thread.sleep(500);
                        cache.update(newBase3);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread threadBlocking = new Thread(
                () -> {
                    try {
                        Base new2Base3 = new Base(cache.getBasebyId(3));
                        new2Base3.setName("new 2 task3");
                        Thread.sleep(500);
                        cache.update(new2Base3);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        threadBlocking.start();
        thread.join();
        threadBlocking.join();
        Assert.assertThat(ex.get().getMessage(), is("Object is blocked"));
    }

    @Test
    public void whenUpdaingNotSimultaneouslyThenNotThrowException() throws InterruptedException {

        final SimpleNonBlockingCache cache = new SimpleNonBlockingCache();
        cache.add(new Base(1, "task1"));
        cache.add(new Base(2, "task2"));
        cache.add(new Base(3, "task3"));

        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        Base newBase3 = new Base(cache.getBasebyId(3));
                        newBase3.setName("new task3");
                        cache.update(newBase3);

                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        Thread threadBlocking = new Thread(
                () -> {
                    try {
                        Base new2Base3 = new Base(cache.getBasebyId(3));
                        new2Base3.setName("new 2 task3");
                        cache.update(new2Base3);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        threadBlocking.start();
        threadBlocking.join();
        assertEquals(cache.getBasebyId(3).getName(), "new 2 task3");
    }
}