package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ43CountOneBetweenOneAndN {

    /**
     * 要点：递归
     */
    public static void main(String[] args) {
        log.info("{}", count(9));
        log.info("{}", count(11));
        log.info("{}", count(1234));
    }

    private static int count(int n) {
        String nStr = String.valueOf(n);
        byte[] nArray = new byte[nStr.length()];
        for (int i = 0; i < nArray.length; i++) {
            char c = nStr.charAt(i);
            nArray[i] = (byte) Integer.parseInt(String.valueOf(c));
        }

        return count(nArray, 0);
    }

    /**
     * 每次调用计算当前最高位1出现的次数
     */
    private static int count(byte[] nArray, int pos) {
        //最后一位1的个数
        if (pos == nArray.length - 1) {
            if (nArray[pos] == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        //求当前最高位1出现次数
        if (nArray[pos] == 0) {
            return 0 + count(nArray, pos + 1);
        } else {
            int num = 0;
            for (int i = pos + 1; i < nArray.length; i++) {
                num = num * 10 + nArray[i];
            }

            return num + 1 + count(nArray, pos + 1);
        }
    }
}
