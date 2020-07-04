package sf.jz;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ40GetLeastNumbers {

    public static void main(String[] args) {
        int[] nums = {3, 1, 2};
        partation(nums, 0, 2);
        log.info("{}", nums);
        get(nums, 3);
        get(nums, 2);
        int[] nums2 = {10, 9, 8, 7, 6, 1, 2, 3, 4, 5, 11};
        get(nums2, 2);
        Queue<Integer> maxHeap = get2(nums2, 3);
        log.info("{}", maxHeap);
        //每次都取出最大值
        maxHeap.poll();
        log.info("{}", maxHeap);
        maxHeap.poll();
        log.info("{}", maxHeap);
        maxHeap.poll();
        log.info("{}", maxHeap);
        maxHeap.poll();
        log.info("{}", maxHeap);
        //remove会报异常
        maxHeap.remove();
        log.info("{}", maxHeap);
    }

    private static Queue<Integer> get2(int[] nums, int k) {
        if (!check(nums, k)) {
            return null;
        }
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : nums) {
            if (maxHeap.size() < k) {
                //填满k个数
                maxHeap.offer(num);
            } else {
                //加入新的最小值
                if (num < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(num);
                }
            }
        }
        return maxHeap;
    }

    private static boolean check(int[] nums, int k) {
        if (k > nums.length) {
            log.warn("k大于数组长度");
            return false;
        }

        if (Objects.isNull(nums) || nums.length == 0) {
            log.warn("数组为空");
            return false;
        }

        if (k <= 0) {
            log.warn("k非法{}", k);
            return false;
        }
        return true;
    }

    private static void get(int[] nums, int k) {
        if (!check(nums, k)) {
            return;
        }
        int begin = 0;
        int end = nums.length - 1;
        int pivotIndex = partation(nums, begin, end);
        while (pivotIndex != k - 1) {
            if (pivotIndex < k - 1) {
                begin = pivotIndex + 1;
                pivotIndex = partation(nums, begin, end);
            } else {
                end = pivotIndex - 1;
                pivotIndex = partation(nums, begin, end);
            }
        }

        for (int index = 0; index < k; index++) {
            log.info("{}", nums[index]);
        }
    }

    /**
     * 遍历交换分组
     */
    private static int partation(int[] nums, int begin, int end) {
        log.info("在{}-{}内分组", begin, end);
        if (begin > end || begin < 0 || end >= nums.length) {
            return -1;
        }

        int pivot = nums[begin];
        int smallIndex = begin;
        int curIndex = begin;
        while (curIndex <= end) {
            if (nums[curIndex] < pivot) {
                smallIndex++;
                if (smallIndex < curIndex) {
                    int tmp = nums[smallIndex];
                    nums[smallIndex] = nums[curIndex];
                    nums[curIndex] = tmp;
                }
            }

            curIndex++;
        }

        if (smallIndex > begin) {
            int tmp = nums[smallIndex];
            nums[smallIndex] = nums[begin];
            nums[begin] = tmp;
        }

        //返回pivot的位置
        return smallIndex;
    }
}
