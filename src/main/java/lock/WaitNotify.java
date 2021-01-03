package lock;

import com.diozero.util.SleepUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        log.info("{}",lock.getClass().getClassLoader());
        Thread waitThread = new Thread(() -> {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        log.info("{} flag is true. wait @ {}", Thread.currentThread(),
                            new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }// 条件满足时，完成工作
            }
            log.info("{} flag is false. wait @ {}", Thread.currentThread(),
                new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }, "WaitThread");
        waitThread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(() -> {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                log.info("{} hold lock. notify @ {}", Thread.currentThread(),
                    new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtil.sleepSeconds(5);
            }
            // 下一行可以演示释放锁后wait线程执行
            // SleepUtil.sleepSeconds(5);
            // 再次加锁
            synchronized (lock) {
                log.info("{} hold lock again. sleep @ {}", Thread.currentThread(),
                    new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtil.sleepSeconds(5);
            }
        }, "NotifyThread");
        notifyThread.start();
    }
}