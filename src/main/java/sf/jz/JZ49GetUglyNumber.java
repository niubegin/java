package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ49GetUglyNumber {

    public static void main(String[] args) {
        log.info("{}", get(1400));
    }

    /**
     * 查找第index个丑数；因子只有2，3，5的数；1是第一个；1，2，3，4，5，6，8，9，10，12，15……
     */
    private static int get(int index) {
        int[] uglyNums = new int[index];
        int lastIndex = 0;
        uglyNums[lastIndex] = 1;
        while (true) {
            if (lastIndex == index - 1) {
                return uglyNums[lastIndex];
            }

            int max2 = getTarget(uglyNums, lastIndex, 2);
            int max3 = getTarget(uglyNums, lastIndex, 3);
            int max5 = getTarget(uglyNums, lastIndex, 5);
            uglyNums[++lastIndex] = getMin(max2, max3, max5);
        }
    }

    /**
     * 求出大于最后数字的最小乘积数
     */
    private static int getTarget(int[] uglyNums, int lastIndex, int target) {
        for (int index = 0; index <= lastIndex; index++) {
            if (uglyNums[index] * target > uglyNums[lastIndex]) {
                return uglyNums[index] * target;
            }
        }

        return 0;
    }

    /**
     * 求3个数中最小
     */
    private static int getMin(int num1, int num2, int num3) {
        int min1 = Math.min(num1, num2);
        return Math.min(min1, num3);
    }
}
