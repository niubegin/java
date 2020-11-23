package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapTest {

    public static void main(String[] args) throws Exception {
//        testMemory();
        Map<String, Object> map = new HashMap<>();
        map.put("list", Arrays.asList("1", "2", "xxxx"));
        map.put("list3", "3");
        List<String> list = (List<String>) map.get("list");
        List<Integer> list2 = (List<Integer>) map.get("list");
        log.info("{}", map.get("list"));
        log.info("{}", list);
        log.info("{}", list2);
        List<Integer> list3 = (List<Integer>) map.get("list3");
        log.info("{}", list3);
    }

    private static void testMemory() {
        //        https://www.cnblogs.com/skywang12345/p/3311092.html
        //https://blog.csdn.net/yangzl2008/article/details/6980709
        //参数设置-Xmx64m -Xms64m
        weakHashMap不访问不释放();
        weakHashMap访问释放();
    }

    private static void weakHashMap不访问不释放() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[1000][1000], new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
        }
    }

    private static void weakHashMap访问释放() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[1000][1000], new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
            for (int j = 0; j < i; j++) {
                System.err.println(j + " size " + maps.get(j).size());
            }
        }
    }
}
