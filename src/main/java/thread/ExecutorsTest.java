package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorsTest {

    public static void main(String[] args) {
        ExecutorsTest executorsTest = new ExecutorsTest();
        executorsTest.start();
    }

    private static final int CONCURRENT_THREAD_COUNT = 4;

    private ExecutorService executorService;
    private ScheduledExecutorService scheduledExecutorService;
    private volatile static ReentrantLock lock = new ReentrantLock();

    public void start() {
        executorService = Executors.newFixedThreadPool(CONCURRENT_THREAD_COUNT);
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleWithFixedDelay(runCheckAsyncTasks, 5, 2, TimeUnit.SECONDS);
    }

    private Runnable runCheckAsyncTasks = () -> {
        try {
            if (lock.tryLock(500L, TimeUnit.MILLISECONDS)) {
                long taskCode = System.currentTimeMillis();
                executorService.submit(getAsyncTaskRunnable(taskCode));
                ThreadPoolExecutor tpe = ((ThreadPoolExecutor) executorService);
                int queueSize = tpe.getQueue().size();
                log.info("task size: {}", queueSize);
            } else {
                log.warn("check async task get lock failed");
            }
        } catch (Exception e) {
            //java.util.concurrent.RejectedExecutionException: Task java.util.concurrent.FutureTask@66ab806f rejected from java.util.concurrent.ThreadPoolExecutor@141dd73a[Running, pool size = 4, active threads = 4, queued tasks = 20, completed tasks = 3]
            //拒绝提交任务时会出现异常
            log.warn("check async task exception: ", e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                log.info("check async task release lock success");
            }
        }
    };

    private Runnable getAsyncTaskRunnable(long taskCode) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    runTask(taskCode);
                } catch (Exception e) {
                    log.warn("run task: {} exception", taskCode, e);
                }
            }
        };
    }

    /**
     * CONCURRENT_THREAD_COUNT个阻塞的线程后，不会有新的任务被调度
     */
    public void runTask(long taskCode) {
        try {
            log.info("run task begin {} - {}", taskCode, taskCode % 2 == 0);
            while (taskCode % 2 == 0) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            log.warn("run {} exception", taskCode, e);
        }

        log.info("run task done {} - {}", taskCode);
    }
}
