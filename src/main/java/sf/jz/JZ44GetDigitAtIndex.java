package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ44GetDigitAtIndex {

    public static void main(String[] args) {
        log.info("{}", get(2));
        log.info("{}", get(6));
        log.info("{}", get(11));
        log.info("{}", get(12));
        log.info("{}", get(13));
    }

    /**
     * 获取数字序列中某一位的数字 0123456
     */
    private static String get(int index) {
        //1特殊处理
        if (index == 1) {
            return "0";
        }
        //先求几位数
        int digitNums = 0;
        int preSum = 0;
        int sum = 1;
        while (sum < index) {
            digitNums++;
            //1位数有9个，0看作是0位的数字
            //2位数有90个，3位数有900个，……
            preSum = sum;
            sum += Math.pow(10, digitNums - 1) * 9;
        }
        //正好相等，返回9
        if (sum == index) {
            return "9";
        }
        //求出哪个数
        int begin = (int) Math.pow(10, digitNums - 1);
        if (digitNums == 1) {
            preSum = 1;
        }
        //多出来多少位数
        int nums = index - preSum;
        //目标数字
        int target = begin + (nums - 1) / digitNums;
        //求目标数字的位数
        int targetIndex = (nums - 1) % digitNums;
        log.info("{},{},{},{},{},{}", index, begin, nums, target, digitNums, targetIndex);
        return String.valueOf(target).substring(targetIndex, targetIndex + 1);
    }
}
