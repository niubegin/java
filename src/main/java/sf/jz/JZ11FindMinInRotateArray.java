package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ11FindMinInRotateArray {

    public static void main(String[] args) {
        int[] arr = {5, 6, 0, 1, 2, 3, 4};
        int[] arr1 = {1, 0, 1, 1, 1};
        int[] arr2 = {1, 1, 1, 0, 1};
        log.info("{}", find(arr, 0, arr.length - 1));
        //log.info("{}", find(arr1, 0, arr1.length - 1));
        //log.info("{}", find(arr2, 0, arr2.length - 1));
    }

    /**
     * 使用二分查找，利用部分递增的特性，缩小查找范围
     */
    private static int find(int[] arr, int low, int high) {
        if (low > high) {
            return arr[low];
        }

        int mid = low + (high - low) / 2;
        //如果大于第一个元素，因为是递增的，说明最小的在右侧
        if (arr[mid] > arr[0]) {
            return find(arr, mid + 1, high);
            //如果左侧大于中间，则中间就是结果
        } else if (arr[mid - 1] > arr[mid]) {
            return arr[mid];
            //在左侧中查找
        } else {
            return find(arr, low, mid - 1);
        }
    }
}
