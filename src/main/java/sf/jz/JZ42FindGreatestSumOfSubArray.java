package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ42FindGreatestSumOfSubArray {

    /**
     * 要点：动态规划
     */
    public static void main(String[] args) {
        int[] nums = {1, -2, 3, 10, -4, 7, 2, -5};
        log.info("{}", find(nums));
        int[] nums2 = {1, -2, 3, 10, -4, -9, 7, 2, -5};
        log.info("{}", find(nums2));
    }

    /**
     * 连续子数组的最大和，加上当前数小于0，则sum重置；sum大于max，则更新max；
     */
    private static int find(int[] nums) {
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            if (sum + num < 0) {
                sum = 0;
            } else {
                sum += num;
            }

            if (sum >= max) {
                max = sum;
            }
        }

        return max;
    }
}
