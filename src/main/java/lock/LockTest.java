package lock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockTest {

    // 需要保证多个线程使用的是同一个锁
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * https://tech.meituan.com/2018/11/15/java-lock.html
     */
    public static void main(String[] args) throws Exception {
        test();
//        retryLock();
    }

    private static void test() {
        new Thread(() -> {
            lock.lock();
            try {
                log.info("{} working", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.info("{} working", Thread.currentThread().getName());
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }, "B").start();
    }

    // ------------------------- 悲观锁的调用方式 -------------------------
    // synchronized
    public synchronized void testMethod() {
        // 操作同步资源
        log.info("synchronized 加锁后，操作同步资源");
    }

    public void modifyPublicResources() {
        try {
            lock.lock();
        } catch (Exception e) {
        } finally {
            // 操作同步资源
            lock.unlock();
        }
    }

    // ------------------------- 乐观锁的调用方式 -------------------------
    // 需要保证多个线程使用的是同一个AtomicInteger
    private AtomicInteger atomicInteger = new AtomicInteger();

    private void happyLock() {
        //执行自增1
        atomicInteger.incrementAndGet();
    }

    private static void retryLock() throws Exception {
        try {
            int times = 0;
            //重试5次加索
            while (times < 5 && !lock.tryLock()) {
                try {
                    Thread.sleep(1000);
                    times++;
                } catch (InterruptedException e) {
                    log.warn("tryLock error: ", e);
                    //运行异常
                    throw new Exception("运行异常");
                }
            }
            //小于尝试次数加锁成功处理业务逻辑
            if (times < 5) {
                log.info("处理业务");
            } else {
                log.info("加索失败");
            }
        } catch (Exception e) {
            log.warn("error: ", e);
            //运行异常
            throw new Exception("运行异常");
        } finally {
            //避免释放其他线程加的锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
