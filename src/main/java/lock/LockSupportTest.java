package lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockSupportTest {

    private static Object blocker = new Object();

    public static void main(String[] args) {
        testBlocker();
    }

    private static void test() {
        Thread a = new Thread(() -> {
            log.info("{}, park", Thread.currentThread().getName());
            LockSupport.park();
            log.info("{}, weak up", Thread.currentThread().getName());
        }, "a");
        a.start();

        Thread b = new Thread(() -> {
            log.info("{}, unpark a", Thread.currentThread().getName());
            //LockSupport.unpark(a);
            a.interrupt();
            log.info("{}, finished", Thread.currentThread().getName());
        }, "b");
        b.start();
    }

    /**
     * unpark可以先于park
     */
    private static void testBlocker() {
        Thread a = new Thread(() -> {
            log.info("{}, park", Thread.currentThread().getName());
            LockSupport.park(blocker);
            log.info("{}, weak up", Thread.currentThread().getName());
        }, "a");
        a.start();

        Thread b = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                log.warn("", e);
            }
            log.info("{}, unpark a", Thread.currentThread().getName());
            LockSupport.unpark(a);
            //a.interrupt();
            log.info("{}, finished", Thread.currentThread().getName());
        }, "b");
        b.start();
    }
}
