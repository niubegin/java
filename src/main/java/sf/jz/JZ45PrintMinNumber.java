package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ45PrintMinNumber {

    /**
     * 要点：快排；新的排序规则；
     */
    public static void main(String[] args) {
        int[] arr = {3, 32, 321};
        sort(arr);
        log.info("{}", arr);
    }

    private static void sort(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return;
        }

        partition(arr, 0, arr.length - 1);
    }

    /**
     * 快排
     */
    private static void partition(int[] arr, int begin, int end) {
        if (begin > end) {
            return;
        }

        int pivot = arr[begin];
        int smallIndex = begin;
        int curIndex = begin;
        while (curIndex <= end) {
            if (compare(pivot, arr[curIndex]) > 0) {
                smallIndex++;
                swap(arr, curIndex, smallIndex);
            }
            curIndex++;
        }

        swap(arr, begin, smallIndex);
        partition(arr, begin, smallIndex - 1);
        partition(arr, smallIndex + 1, end);
    }

    /**
     * 新的排序规则
     */
    private static int compare(int a, int b) {
        String ab = String.valueOf(a) + String.valueOf(b);
        String ba = String.valueOf(b) + String.valueOf(a);
        return ab.compareTo(ba);
    }

    /**
     * 交换
     */
    private static void swap(int[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
