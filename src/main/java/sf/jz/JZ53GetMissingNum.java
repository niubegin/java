package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ53GetMissingNum {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 4, 5, 6, 7};
        log.info("{}", get(arr, 0, arr.length - 1));
    }

    private static int get(int[] arr, int begin, int end) {
        if (begin > end) {
            return -1;
        }

        int mid = (end - begin) / 2;
        if (arr[mid] == mid) {
            // 相等时，从后半部分查找
            return get(arr, mid + 1, end);
        } else {
            if (mid < 1) {
                return -1;
            } else {
                if (arr[mid - 1] == mid - 1) {
                    // 检查前一个，相等的话就是本身
                    return mid;
                } else {
                    // 前一个不等的话，继续二分查找
                    return get(arr, begin, mid - 1);
                }
            }
        }
    }
}
