import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatitleTest {
    static volatile int count = 0;
    static int count2 = 0;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    static class Count implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                count++;
                count2++;
                atomicInteger.getAndIncrement();
            }
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(new Count());
        pool.execute(new Count());
        countDownLatch.await();
        System.out.println(count);
        System.out.println(count2);
        System.out.println(atomicInteger);
        pool.shutdown();
    }
}
