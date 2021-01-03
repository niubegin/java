package lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTest2 {

    /**
     * 可重入； synchronized(对象)方式使用的monitorenter/monitorexit/monitorexit指令实现，与synchronized方法不同；
     */
    public static void main(String[] args) {
        SynchronizedTest2 synchronizedTest = new SynchronizedTest2();
        synchronizedTest.m1();
    }

    public void m1() {
        synchronized (this) {
            m2();
        }
    }

    public void m2() {
        synchronized (this) {
            m3();
        }
    }

    public void m3() {
        synchronized (this) {
        }
    }
}
