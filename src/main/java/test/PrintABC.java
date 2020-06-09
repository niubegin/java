package test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程交替打印ABC的多种实现方法
 * https://blog.csdn.net/xiaokang123456kao/article/details/77331878
 */
public class PrintABC {

    //用于等待每种打印方法结束后换行
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        //m01();
        System.out.println();
        m02();
        cyclicBarrier.await();
        System.out.println();
        cyclicBarrier.reset();
        m03();
        cyclicBarrier.await();
        System.out.println();
        cyclicBarrier.reset();
        m04();
        cyclicBarrier.await();
        System.out.println();
        cyclicBarrier.reset();
        m05();
        cyclicBarrier.await();
        System.out.println();
        cyclicBarrier.reset();
        m06();
        cyclicBarrier.await();
        System.out.println();
    }
    /**
     * 使用synchronized实现
     */
    /**
     * 经博友：璐璐的宝宝的指点，原程序虽然也能完成任务，但是存在一个很大的缺陷。为了对比一下，这里保留原实现，并给出新的改进实现。
     * 原实现：
     */
    public static class ThreadPrinter implements Runnable {
        private String name;
        private Object prev;
        private Object self;

        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {// 多线程并发，不能用if，必须使用whil循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);//打印
                        count--;

                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    //此时执行完self的同步块，这时self锁才释放。
                    try {
                        prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        /**
                         * JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
                         */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void m01() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("A", c, a);
        ThreadPrinter pb = new ThreadPrinter("B", a, b);
        ThreadPrinter pc = new ThreadPrinter("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);//保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

    /**
     * 使用synchronized实现
     */
    public static class ThreadPrinter02 implements Runnable {
        private String name;
        private Object prev;
        private Object self;

        private ThreadPrinter02(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {// 多线程并发，不能用if，必须使用whil循环
                synchronized (prev) { // 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);// 打印
                        count--;

                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (count == 0) {// 如果count==0,表示这是最后一次打印操作，通过notifyAll操作释放对象锁。
                            prev.notifyAll();
                        } else {
                            prev.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (name.equals("C")) {
                try {
                    System.out.println();
                    System.out.println("m02打印完最后一个C等待");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void m02() throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter02 pa = new ThreadPrinter02("A", c, a);
        ThreadPrinter02 pb = new ThreadPrinter02("B", a, b);
        ThreadPrinter02 pc = new ThreadPrinter02("C", b, c);

        new Thread(pa).start();
        Thread.sleep(10);// 保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

    /**
     * 使用lock实现
     */
    private static Lock lock = new ReentrantLock();// 通过JDK5中的Lock锁来保证线程的访问的互斥
    private static int state = 0;//通过state的值来确定是否打印

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("A");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("B");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {// 多线程并发，不能用if，必须用循环测试等待条件，避免虚假唤醒
                        System.out.print("C");
                        state++;
                        i++;
                    }
                } finally {
                    lock.unlock();// unlock()操作必须放在finally块中
                }
            }
            try {
                System.out.println();
                System.out.println("m03打印完最后一个C等待");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m03() {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }


    /**
     * 使用lock和condition实现
     */
    private static Lock lock4 = new ReentrantLock();
    private static Condition A = lock4.newCondition();
    private static Condition B = lock4.newCondition();
    private static Condition C = lock4.newCondition();

    private static int count = 0;

    static class ThreadA4 extends Thread {
        @Override
        public void run() {
            try {
                lock4.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0) {//注意这里是不等于0，也就是说在count % 3为0之前，当前线程一直阻塞状态
                        A.await(); // A释放lock锁
                    }
                    System.out.print("A");
                    count++;
                    B.signal(); // A执行完唤醒B线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock4.unlock();
            }
        }
    }

    static class ThreadB4 extends Thread {
        @Override
        public void run() {
            try {
                lock4.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1) {
                        B.await();// B释放lock锁，当前面A线程执行后会通过B.signal()唤醒该线程
                    }
                    System.out.print("B");
                    count++;
                    C.signal();// B执行完唤醒C线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock4.unlock();
            }
        }
    }

    static class ThreadC4 extends Thread {
        @Override
        public void run() {
            try {
                lock4.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        C.await();// C释放lock锁
                    }
                    System.out.print("C");
                    count++;
                    A.signal();// C执行完唤醒A线程
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock4.unlock();
            }
            try {
                System.out.println();
                System.out.println("m04打印完最后一个C等待");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m04() throws InterruptedException {
        new ThreadA4().start();
        new ThreadB4().start();
        new ThreadC4().start();
    }


    /**
     * 使用Semaphore实现
     */
    // 以A开始的信号量,初始信号量数量为1
    private static Semaphore SA = new Semaphore(1);
    // B、C信号量,A完成后开始,初始信号数量为0
    private static Semaphore SB = new Semaphore(0);
    private static Semaphore SC = new Semaphore(0);

    static class ThreadA05 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    SA.acquire();// A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    System.out.print("A");
                    SB.release();// B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB05 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    SB.acquire();
                    System.out.print("B");
                    SC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadC05 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    SC.acquire();
                    System.out.print("C");
                    SA.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                System.out.println();
                System.out.println("m05打印完最后一个C等待");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void m05() throws InterruptedException {
        new ThreadA05().start();
        new ThreadB05().start();
        new ThreadC05().start();
    }

    public static void m06() throws InterruptedException {
        Semaphore sa = new Semaphore(1);
        Semaphore sb = new Semaphore(0);
        Semaphore sc = new Semaphore(0);
        ExecutorService executorService = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(3));
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sa.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.print('A');
                sb.release();
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sb.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.print('B');
                sc.release();
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    sc.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.print('C');
                sa.release();
            }
            try {
                System.out.println();
                System.out.println("m06打印完最后一个C等待");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

}
