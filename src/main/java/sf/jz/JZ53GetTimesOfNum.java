package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ53GetTimesOfNum {

    /**
     * 要点：二分查找；优化：使用查找首尾位置计算
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        log.info("{}", get(arr, 3));
        log.info("{}", get(arr, 2));
        log.info("{}", getFirst(arr, 0, arr.length - 1, 2));
        log.info("{}", getLast(arr, 0, arr.length - 1, 2));
        log.info("{}", getByFirstAndLast(arr, 2));
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

    private static int getFirst(int[] arr, int begin, int end, int target) {
        if (begin > end) {
            return -1;
        }
        int mid = (end - begin) / 2;
        if (arr[mid] == target) {
            if (mid == 0) {
                return -1;
            } else {
                if (arr[mid - 1] == target) {
                    // 在左侧查找
                    return getFirst(arr, begin, mid - 1, target);
                } else {
                    return mid;
                }
            }
        } else {
            // 在右侧查找
            return getFirst(arr, mid + 1, end, target);
        }
    }

    private static int getLast(int[] arr, int begin, int end, int target) {
        if (begin > end) {
            return -1;
        }

        int mid = (end - begin) / 2;
        if (arr[mid] == target) {
            if (mid == end) {
                return mid;
            }

            if (arr[mid + 1] == target) {
                return getLast(arr, mid + 1, end, target);
            } else {
                return mid;
            }
        } else {
            return getLast(arr, mid + 1, end, target);
        }
    }

    private static int getByFirstAndLast(int[] arr, int target) {
        int first = getFirst(arr, 0, arr.length, target);
        if (first == -1) {
            return 0;
        }
        int last = getLast(arr, 0, arr.length, target);
        if (last == -1) {
            return 0;
        }
        return last - first + 1;
    }
}
