package thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelStreamTest {

    /**
     * 验证并发流在写入容器线程不安全情况下，丢失数据的问题
     */
    public static void main(String[] args) {
        int repeatTimes = 100;
        for (int i = 0; i < repeatTimes; i++) {
            //存在并发问题的写法：写入的容器，线程不安全
            listTest(new ArrayList<>(), new ArrayList<>());
            mapTest(new HashMap<>(), new HashMap<>());
            mapTest(new ConcurrentHashMap<>(), new HashMap<>());
            //并发安全的写法：写入的容器，需要是线程安全的
            listTest(new ArrayList<>(), new CopyOnWriteArrayList<>());
            mapTest(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
            mapTest(new HashMap<>(), new ConcurrentHashMap<>());
        }
    }

    /**
     * 存在报异常情况：Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException
     */
    private static void listTest(List<String> sourceList, List<String> targetList) {
        int expectedSize = 20;
        for (int i = 0; i < expectedSize; i++) {
            sourceList.add("a" + i);
        }

        try {
            sourceList.parallelStream().forEach(item -> targetList.add(item));
        } catch (Exception e) {
            log.warn("parallel write exception:", e);
        }

        if (targetList.size() < expectedSize) {
            log.warn("target container: {}, expected size:{}, real size: {}",
                targetList.getClass(), expectedSize, targetList.size());
        }
    }

    private static void mapTest(Map<String, String> sourceMap, Map<String, String> targetMap) {
        int expectedSize = 100;
        for (int i = 0; i < expectedSize; i++) {
            sourceMap.put("a" + i, "a" + i);
        }

        sourceMap.values().parallelStream().forEach(v -> targetMap.put(v, v));
        if (targetMap.size() < expectedSize) {
            log.warn("target container: {}, expected size:{}, real size: {}",
                targetMap.getClass(), expectedSize, targetMap.size());
        }
    }
}
