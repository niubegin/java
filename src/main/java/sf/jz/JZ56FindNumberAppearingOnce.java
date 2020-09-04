package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ56FindNumberAppearingOnce {

    /**
     * 要点：位运算
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 4, 3, 4, 4, 3};
        log.info("{}", get(arr));
    }

    private static int intLength = 32;

    private static int get(int[] arr) {
        int[] target = new int[intLength];
        for (int num : arr) {
            int mask = 1;
            //计算每一位是否为1，加到target对应的位上
            //从个数位开始
            for (int i = intLength - 1; i >= 0; i--) {
                if ((num & mask) != 0) {
                    target[i] += 1;
                }

                mask = mask << 1;
            }
        }

        int result = 0;
        //从最高位开始
        for (int i = 0; i < intLength; i++) {
            //先进位
            result = result << 1;
            //每位求模结果加上
            result += target[i] % 3;
        }

        return result;
    }
}
