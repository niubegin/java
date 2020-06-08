package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ17PrintNBitsNumber {

    public static void main(String[] args) {
        int n = 3;
        print(n);
        byte[] number = new byte[n];
        setAndPrint(number, n);
    }

    /**
     * 递归打印，枚举各位数字，代码简练
     */
    private static void setAndPrint(byte[] number, int n) {
        for (byte i = 0; i <= 9; i++) {
            //设置第n位
            number[n - 1] = i;
            //如果n==1，则完成了各位的设置，打印输出
            if (n == 1) {
                print(number);
            } else {
                //设置第n-1位
                setAndPrint(number, n - 1);
            }
        }
    }

    /**
     * 打印1-n位数，使用byte数组去模拟递增
     */
    private static void print(int n) {
        byte[] number = new byte[n];
        while (!checkOver(number)) {
            incAndPrint(number);
        }
    }

    /**
     * 检查是否打印结束
     */
    private static boolean checkOver(byte[] number) {
        for (byte c : number) {
            if (c != 9) {
                return false;
            }
        }
        return true;
    }

    /**
     * 增加并打印
     */
    private static void incAndPrint(byte[] number) {
        inc(number);
        print(number);
    }

    /**
     * 增加
     */
    private static void inc(byte[] number) {
        byte added = 1;
        for (int i = 0; i < number.length; i++) {
            byte n = number[i];
            n = (byte) (n + added);
            //需要进位，则本位设置为0，上一位+1
            if (n >= 10) {
                number[i] = 0;
                added = 1;
            } else {
                number[i] = n;
                //不需要进位则加数完成
                break;
            }
        }
    }

    /**
     * 打印
     */
    private static void print(byte[] number) {
        StringBuilder sb = new StringBuilder(number.length);
        boolean needPrint = false;
        //打印
        for (int i = number.length - 1; i >= 0; i--) {
            //以前都是0，本位不是0，设置标记位
            if (number[i] != 0 && !needPrint) {
                needPrint = true;
            }
            //以前都不是0才加到打印字符串
            if (needPrint) {
                sb.append(number[i]);
            }
        }
        log.info("{}", sb.toString());
    }
}
