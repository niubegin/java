package sf.jz;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

@Slf4j
public class JZ33VerifySequenceOfBST {

    public static void main(String[] args) {
        int[] array = {5, 7, 6, 9, 11, 10, 8};
        log.info("{}", verify(array));
        int[] array2 = {7, 4, 6, 5};
        log.info("{}", verify(array2));
        int[] array3 = {1, 2, 3};
        log.info("{}", verify(array3));
    }

    private static boolean verify(int[] array) {
        if (Objects.isNull(array)) {
            return false;
        }
        return verify(array, 0, array.length - 1);
    }

    /**
     * 数组可以以最后一个元素来分成小大两部分则true
     */
    private static boolean verify(int[] array, int start, int end) {
        if (start >= end) {
            return true;
        }
        int pivot = array[end];
        boolean small = true;
        int maxSmallIndex = start;
        for (int index = start; index < end; index++) {
            if (array[index] > pivot) {
                if (small) {
                    small = false;
                    maxSmallIndex = start - 1;
                }
            } else {
                //发现了新的小于的，则返回false
                if (!small) {
                    return false;
                }
            }
        }

        return verify(array, start, maxSmallIndex) && verify(array, maxSmallIndex + 1, end - 1);
    }
}
