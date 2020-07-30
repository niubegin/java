package test;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LRUMap {

    private static final int cacheSize = 5;
    /**
     * LRU缓存
     */
    private static Map<String, Integer> lruCache =
        new LinkedHashMap<String, Integer>((int) Math.ceil(cacheSize / 0.75f) + 1,
            0.75f,
            true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > cacheSize;
            }
        };

    public static void main(String[] args) {
        lruCache.put("AAA___BBBB1", 1);
        lruCache.put("AAA___BBBB2", 1);
        lruCache.put("AAA___BBBB3", 1);
        lruCache.put("AAA___BBBB4", 1);
        lruCache.put("AAA___BBBB5", 1);
        log.info("{}", lruCache);
        lruCache.put("AAA___BBBB6", 1);
        log.info("{}", lruCache);
    }
}
