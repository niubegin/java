package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ53GetTimesOfNum {

    /**
     * 要点：二分查找
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        log.info("{}", get(arr, 3));
        log.info("{}", get(arr, 2));
    }

    /**
     * 获取数字在排序数组中出现的次数
     */
    private static int get(int[] arr, int target) {
        int anyIndex = get(arr, 0, arr.length, target);
        if (anyIndex == -1) {
            return -1;
        }
        int begin = getEdge(arr, anyIndex, target, -1);
        int end = getEdge(arr, anyIndex, target, 1);
        return end - begin + 1;
    }

    private static int get(int[] arr, int begin, int end, int target) {
        int mid = (end - begin) / 2;
        if (arr[mid] > target) {
            if (mid - 1 < begin) {

            } else {
                return get(arr, begin, mid - 1, target);
            }
        } else if (arr[mid] == target) {
            return mid;
        } else {
            if (mid + 1 > end) {

            } else {
                return get(arr, mid + 1, end, target);
            }
        }

        return -1;
    }

    private static int getEdge(int[] arr, int index, int target, int direct) {
        for (int i = index; i < arr.length; i += direct) {
            if (arr[i] != target) {
                return i -= direct;
            }
        }

        return -1;
    }
}
