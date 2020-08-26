package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZGetValueEqualIndexNum {

    public static void main(String[] args) {
        int[] arr = {-3, -2, 0, 3};
        log.info("{}", get(arr, 0, arr.length));
    }

    private static int get(int[] arr, int begin, int end) {
        if (begin > end) {
            return -1;
        }

        int mid = (end + begin) / 2;
        if (arr[mid] == mid) {
            // 相等，则找到返回本身
            return mid;
        } else if (arr[mid] > mid) {
            // 值大于索引，则在左侧查找
            return get(arr, begin, mid - 1);
        } else {
            // 值小于索引，则在右侧查找
            return get(arr, mid + 1, end);
        }
    }
}
