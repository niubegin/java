import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LockTest {

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
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
