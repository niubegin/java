package lock;

public class SynchronizedTest0 {

    public static void main(String[] args) {
        synchronized (SynchronizedTest0.class) {
        }
        m1();
    }

    public static synchronized void m1() {
    }
}
