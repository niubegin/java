package test;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javassist.CannotCompileException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {

    private static Logger logger = LoggerFactory.getLogger(ExceptionTest.class);

    /**
     * 异常代码模拟
     */
    public static void main(String[] args) {
        //javaLangStackOverflowError();
        //javaLangOutOfMemoryErrorJavaHeapSpace();

        ExceptionTest thief = new ExceptionTest();
        //thief.callManyNPEInLoop();

        //castNull();
        //throwNull();
        try {
            throwFinallyTest();
        } catch (IllegalArgumentException e) {
            logger.warn("outer IllegalArgumentException");
        }
    }

    private static void throwFinallyTest() {
        try {
            try {
                throw null;
            } catch (Exception e) {
                logger.error("e:", e);
                throw new IllegalArgumentException("");
            }
        } catch (IllegalArgumentException ex) {
            logger.warn("IllegalAccessException", ex);
            //有finally时，抛出的新异常会被吞掉
            //throw new IllegalAccessException("");
        } catch (Exception e) {
            logger.warn("Exception", e);
        } finally {
            //https://www.iteye.com/blog/shift-alt-ctrl-2156937
            throw new IllegalArgumentException("");
        }
    }

    private static void throwNull() {
        try {
            throw null;
        } catch (Exception e) {
            logger.error("e:", e);
        }
    }

    private static void castNull() {
        Long value = null;
        try {
            test(value);
        } catch (Exception e) {
            logger.warn("aaa{}", e);
            e.printStackTrace();
        }
    }

    public static long test(long value) {
        return value;
    }


    public void callManyNPEInLoop() {
        for (int i = 0; i < 200000; i++) {
            try {
                ((Object) null).getClass();
            } catch (Exception e) {
                String longMsg = "生成随机补货任务失败，补货任务：数据RepTaskModel [detailID=339814, batchId=1584164060564, asnCode=A20200314-000044, putAwayBinCode=C000273B1A, state=REPLENISHED, runningMode=RANDOM, available=true, toString()=TaskModel [taskID=15841657396882191, skuCode=6948852955601, skuName=null, shelfCode=C000273, binCode=C000273B1A, count=192, completedCount=192, inventoryBatchNo=LT1584158135153, generateTime=1584165739688, startTime=0, endTime=1584165739688, geekPlusSessionID=79cd19aa-aa33-4522-b5ed-3c6048468391, packingCode=null, packingSpec=null, miniPackingAmount=0, secondPackingAmount=0, thirdPackingAmount=0, miniPackingBackAmount=0, secondPackingBackAmount=0, thirdPackingBackAmount=0]]，需要手动处理，异常信息为：";
                // This will switch from 2 to 0 (indicating our problem is happening)
                //System.out.println(e.getStackTrace().length);
                //System.out.println("" + i + e);
                logger.error("i:{}, longmsg:{}, e:", i, longMsg, e);

                /**
                 * 开始的输出
                 * 18:42:34.228 [main] ERROR test.ExceptionTest - e:
                 * java.lang.NullPointerException: null
                 * 	at test.ExceptionTest.callManyNPEInLoop(test.ExceptionTest.java:30) [classes/:?]
                 * 	at test.ExceptionTest.main(test.ExceptionTest.java:24) [classes/:?]
                 * 	次数多了后
                 * 	18:43:11.355 [main] ERROR test.ExceptionTest - e:
                 * java.lang.NullPointerException: null
                 */
            }
        }
    }

    /**
     * 在一个函数中调用自己就会产生这个错误 无限递归调用，没有退出条件 java.lang.StackOverflowError
     */
    @Test
    public void javaLangStackOverflowError() {
        javaLangStackOverflowError();
    }

    /**
     * 申请大对象，超出堆内存 -Xmx5m -Xms5m java.lang.OutOfMemoryError: Java heap space
     */
    @Test
    public void javaLangOutOfMemoryErrorJavaHeapSpace() {
        byte[] data = new byte[10 * 1024 * 1024];
    }

    /**
     * https://blog.csdn.net/renfufei/article/details/77585294 选用不同的GC算法, 产生的错误信息也不相同 -Xmx5m -Xms5m -XX:+UseParallelGC
     * java.lang.OutOfMemoryError : GC overhead limit exceeded -Xmx5m -Xms5m -XX:+UseG1GC java.lang.OutOfMemoryError:
     * Java heap space
     */
    @Test
    public void javaLangOutOfMemoryErrorGCOverheadLimitExceeded() {
        Map<Integer, String> map = new HashMap<>();
        Random r = new Random();
        while (true) {
            map.put(Integer.valueOf(r.nextInt()), "value");
        }
    }

    /**
     * ByteBuffer使用直接内存，超出时报错： java.lang.OutOfMemoryError: Direct buffer memory -XX:MaxDirectMemorySize=5m
     */
    @Test
    public void javaLangOutOfMemoryErrorDirectBufferMemory() {
        System.out.println("maxDirectMemory : " + sun.misc.VM.maxDirectMemory() / (1024 * 1024) + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    /**
     * java.lang.OutOfMemoryError: Unable to create new native thread 64-bit Mac OS X 10.9, Java 1.7.0_45 – JVM 在创建
     * #2031 号线程之后挂掉 64-bit Ubuntu Linux, Java 1.7.0_45 – JVM 在创建 #31893 号线程之后挂掉 64-bit Windows 7, Java 1.7.0_45 –
     * 由于操作系统使用了不一样的线程模型, 这个错误信息似乎不会出现. 创建 #250,000 号 线程之后,Java进程依然存在, 但虚拟内存(swap file) 的使用量达到了 10GB,
     * 系统运行极其缓慢,基本上没法运行了。
     */
    @Test
    public void javaLangOutOfMemoryErrorUnableToCreateNewNativeThread() {
        int num = 0;
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                }
            }).start();
            System.out.println(num++);
        }
    }

    /**
     * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace -XX:MaxMetaspaceSize=50m
     */
    @Test
    public void javaLangOutOfMemoryErrorMetaspace() throws CannotCompileException {
        System.out.println("生成过多类");
        javassist.ClassPool cp = javassist.ClassPool.getDefault();
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("demo.Generated" + i).toClass();
            System.out.println(i);
        }
    }
}
