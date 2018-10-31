/**
 * 6.2.1. Проиллюстрировать проблемы с многопоточностью.
 *
 * Современные компьютеры зачастую имеют больше одного процессора. И процессоры зачастую имею больше одного ядра
 * При многопоточном программировании, часто бывает ситуация, что несколько потоков выполняются одновременно на разных
 * процессорах или ядрах. Физическое устройство оперативной памяти компьютера таково, что помимо общей оперативной
 * памяти у каждого процессора/ядра существуют регистры и кэш. При выполнении вычислений, данные копируются из
 * основной оперативной памяти в кэш и регистры процессора и обратно. В связи с этим возникают проблемные ситуации,
 * основные из которых:
 *
 *  1. Видимость модификации общих объектов (visibility of shared objects)
 *  2. Состояние гонки (race condition)
 *
 * 1. Если несколько потоков делят объект, то изменения в нем сделанные одним потоком могут быть не видны остальным
 *  потокам. Например, свойство count объекта в оперативной пямяти имеет значение = 1. Первый поток, который работает на
 *  процессоре 1, изменяет значение свойства на 2. Значение свойства меняется на 2 и записывается в кэш процессора 1.
 *  При этом в основной оперативной памяти, значение count всё еще 1. Поток, работающий на процессоре 2,
 *  производит вычисления над тем же свойством count. Для второго потока count = 1,  так как значение еще не изменилось
 *  в основной оперативной памяти.
 *
 * 2. При одновременном выполнении нескольких потоков может возникнуть ситуация, когда результаты работы программы
 * будут разными в зависимости от порядка выполнения потоков. Например, если поток 1 пытается записать в свойство
 * count общего объекта значение 1, а второй поток значение 2, то при каждом запуске программы, результат не определен.
 * Значение count может быть или 1 или 2, в зависимости от того, какой поток выполнился позже.
 */

package dstelmachenko.multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MultithreadingProblems {
    public static final class Counter {
        @GuardedBy("this")
        long count = 0;
        static String name = "Name";
        final Object lock = new Object();

        public void add(long value) {
            //synchronized (this.lock) {
                this.count += value;
            //}
        }
    }

    public static final class CounterThread extends Thread {
        @GuardedBy("this")
        protected final Counter counter;

        public CounterThread(final Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            //synchronized (counter) {
                counter.add(1);
            //}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread threadA = new CounterThread(counter);
        Thread threadB = new CounterThread(counter);
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println(counter.count);
    }
}

