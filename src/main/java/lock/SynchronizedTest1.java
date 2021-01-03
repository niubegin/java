package lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynchronizedTest1 {

    public static void main(String[] args) {
        SynchronizedTest1 synchronizedTest1 = new SynchronizedTest1();
        synchronizedTest1.m1();
    }

    public synchronized void m1() {
        m2();
    }

    public synchronized void m2() {
        m3();
    }

    public synchronized void m3() {
    }
}
