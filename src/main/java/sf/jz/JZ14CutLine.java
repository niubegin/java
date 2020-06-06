package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ14CutLine {

    public static void main(String[] args) {
        int length = 18;
        log.info("{}:{}:{}", length, max1(length), max2(length));
    }

    /**
     * 动态规划算法实现
     */
    private static int max1(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int[] max = new int[length + 1];
        // 0-3不是分割的结果，分割的结果是上面返回的情况；
        // 但是4-length是分割的结果
        max[0] = 0;
        max[1] = 1;
        max[2] = 2;
        max[3] = 3;
        int maxI = 0;
        int tmp = 0;
        //从下向上求值
        for (int i = 4; i <= length; i++) {
            maxI = 0;
            //穷举求最大
            for (int j = 1; j <= i / 2; j++) {
                tmp = max[j] * max[i - j];
                if (tmp > maxI) {
                    maxI = tmp;
                }
            }
            max[i] = maxI;
        }
        log.info("{}", max);
        return max[length];
    }

    /**
     * 贪婪算法：首先按3剪断，余下的按2剪（余下1时借一个3来剪），贪婪算法需要数学证明
     */
    private static int max2(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 3;
        }
        //最多分成几个3
        int max3 = length / 3;
        //余数
        int left = length % 3;
        //余数0则left=1
        if (left == 0) {
            left = 1;
        } else if (left == 1) {
            //余数为1则占用一个3，分成2个2
            max3--;
            left = 4;
        }
        //余数2不处理
        if (left == 2) {
        }
        return (int) (Math.pow(3, max3) * left);
    }
}
