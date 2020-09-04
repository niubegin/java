package sf.jz;

import java.util.Arrays;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ57FindNumsWithSum {

    /**
     * 要点：双指针；排序
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 7, 11, 15};
        log.info("{}", get(nums, 15));
        log.info("{}", get(nums, 16));
        log.info("{}", get(nums, 17));
        log.info("{}", get(nums, 18));
        log.info("{}", get(nums, 19));
        log.info("{}", get(nums, 20));
    }

    private static int[] get(int[] nums, int sum) {
        if (Objects.isNull(nums)) {
            return null;
        }

        int low = 0, high = nums.length - 1;
        int tmp = 0;
        while (low < high) {
            tmp = nums[low] + nums[high];
            if (tmp > sum) {
                high--;
            } else if (tmp == sum) {
                return new int[]{nums[low], nums[high]};
            } else {
                low++;
            }
        }

        return null;
    }
}
