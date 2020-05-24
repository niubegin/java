import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MapTest {
    public static void main(String[] args) throws Exception {
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
