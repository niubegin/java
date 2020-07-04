package sf.jz;

import java.util.HashMap;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ39FindOutMoreThanHalfNum {

    public static void main(String[] args) {
        Integer[] nums = {1, 2, 2, 4};
        int[] nums1 = {1, 2, 2, 2, 4};
        log.info("{}", find(nums));
        log.info("{}", find2(nums1));
    }

    /**
     * 查找超过一半的数组，空间复杂度O(n)
     */
    private static Integer find(Integer[] nums) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (Integer num : nums) {
            if (Objects.isNull(res.get(num))) {
                res.put(num, 1);
            } else {
                if (res.get(num) == nums.length / 2) {
                    return num;
                }

                res.put(num, res.get(num) + 1);
            }
        }

        return null;
    }

    /**
     * 计数法：等于当前数加一，不等减一，计数等于零，取当前数
     */
    private static int find2(int[] nums) {
        int result = nums[0];
        int times = 1;
        for (int index = 1; index < nums.length; index++) {
            if (result == nums[index]) {
                times++;
            } else {
                times--;
                if (times == 0) {
                    result = nums[index];
                }
            }
        }

        return result;
    }
}
