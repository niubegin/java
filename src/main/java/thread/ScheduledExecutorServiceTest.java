package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduledExecutorServiceTest {

    private final static ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        //fixRateTest();
        ScheduledExecutorServiceTest tst = new ScheduledExecutorServiceTest();
        tst.start();
    }

    private static void fixRateTest() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    int[] s = new int[1];
                    log.info("OK");
                    log.info("", s[1]);  // 数组越界
                } catch (Exception e) {
                    log.warn("异常：", e);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private ScheduledExecutorService scheduledExecutorService;

    private static final int CONCURRENT_THREAD_COUNT = 4;

    public void start() {
        executorService = Executors.newFixedThreadPool(CONCURRENT_THREAD_COUNT);
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(runCheckAsyncTasks, 5, 2, TimeUnit.SECONDS);
    }

    private ReentrantLock lock = new ReentrantLock();
    private Runnable runCheckAsyncTasks = () -> {
        try {
            if (lock.tryLock(500L, TimeUnit.MILLISECONDS)) {
                List<String> tasks = Arrays.asList("1", "2", "3");

                if (tasks != null && !tasks.isEmpty()) {
                    for (String asyncTaskEntity : tasks) {
                        //提交任务捕获不到异常
                        try {
                            executorService.submit(getAsyncTaskRunnable(asyncTaskEntity));
                        } catch (Exception e) {
                            log.warn("异常：", e);
                        }
                    }
                    throw new RuntimeException("tt");
                }
            }
        } catch (Exception e) {
            log.warn("", e);
        } finally {
            log.info("释放了锁.");
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    };

    private Runnable getAsyncTaskRunnable(String task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("task: {}", task);
                    int[] s = new int[1];
                    log.info("", s[1]);  // 数组越界
                } catch (Exception e) {
                    log.warn("异常发生");
                }
            }
        };
    }
}
