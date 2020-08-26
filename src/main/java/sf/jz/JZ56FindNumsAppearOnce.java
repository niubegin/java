package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ56FindNumsAppearOnce {

    /**
     * 思路：https://zhuanlan.zhihu.com/p/40652166 要点：异或；分组；
     */
    public static void main(String[] args) {
        log.info("{}", 0 ^ 6);
        int[] arr = {1, 2, 3, 2, 4, 3};
        int[] res = new int[2];
        get(arr, res);
        log.info("{}", res);
    }

    private static void get(int[] arr, int[] res) {
        if (arr.length == 0) {
            return;
        }

        // 所有数字异或
        int num = 0;
        for (int n : arr) {
            num ^= n;
        }

        int index = findFirstBitIs1(num);
        int num1 = 0, num2 = 0;
        for (int n : arr) {
            if (isBit1(n, index)) {
                log.info("index: {} is 1:{}", index, n);
                num1 ^= n;
            } else {
                log.info("index: {} is 0:{}", index, n);
                num2 ^= n;
            }
        }

        res[0] = num1;
        res[1] = num2;
    }

    /**
     * 求第一个不为0的bit位
     */
    private static int findFirstBitIs1(int num) {
        int index = 0;
        while (((num & 1) == 0) && index < Integer.SIZE) {
            num = num >> 1;
            index++;
        }

        return index;
    }

    /**
     * 判断指定位是不是1
     */
    private static boolean isBit1(int num, int index) {
        num = num >> index;
        return (num & 1) == 1;
    }
}
