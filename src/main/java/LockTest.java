import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock();
        try {
            int times = 0;
            //重试5次加索
            while (!lock.tryLock() && times < 5) {
                try {
                    Thread.sleep(1000);
                    times++;
                } catch (InterruptedException e) {
                    //logger.warn("tryLock error: ", e);
                    //运行异常
                    throw new Exception("运行异常");
                }
            }
//小于尝试次数加锁成功处理业务逻辑
            if (times < 5) {

            }
        } catch (Exception e) {
            //logger.warn("error: ", e);
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
