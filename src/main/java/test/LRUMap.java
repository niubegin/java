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
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        int cap = (int) Math.ceil(500 / 0.75f) + 1;
        log.info("{},{}", cap, tableSizeFor(cap));
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
