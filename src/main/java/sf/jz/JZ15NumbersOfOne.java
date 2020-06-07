package sf.jz;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.CurrentOperations;

@Slf4j
public class JZ15NumbersOfOne {

    /**
     * 好多种算法：https://www.cnblogs.com/graphics/archive/2010/06/21/1752421.html java版：https://blog.csdn.net/u013132035/article/details/80554731
     */
    public static void main(String[] args) {
        count(9);
        //count(-1);
        count2(9);
        count3(9);
        count2(-1);
        count3(-1);
        count2(0xFFFFFFFF);
        count3(0xFFFFFFFF);
        count2(0x80000000);
        count3(0x80000000);
        check2power(9);
        check2power(8);
        countChangeNumber(8, 9);
    }

    /**
     * 推荐解法： 把一个整数减去1，再和原来整数做与运算，会把该整数最右边一个1变成0,。那么一个整数的二进制表示中有多少个1，就可以进行多少次这样的操作。
     */
    private static int count3(int number) {
        int n = number;
        int count = 0;
        while (number != 0) {
            count++;
            number = (number - 1) & number;
        }
        log.info("{}:{}", n, count);
        return count;
    }

    /**
     * 负数时会死循环，符号位是1
     */
    private static int count(int number) {
        int count = 0;
        while (number != 0) {
            if ((number & 1) != 0) {
                count++;
            }

            number = number >> 1;
        }

        log.info("{}:{}", number, count);
        return count;
    }

    /**
     * 不修改number，移动标尺做与运算
     */
    private static int count2(int number) {
        int count = 0;
        int rule = 1;
        while (rule != 0) {
            if ((number & rule) != 0) {
                count++;
            }
            rule = rule << 1;
        }
        log.info("{}:{}", number, count);
        return count;
    }

    /**
     * 用一条语句判断一个整数是不是2的整数次方。
     */
    private static boolean check2power(int n) {
        boolean flag = (n & (n - 1)) == 0;
        log.info("{}:{}", n, flag);
        return flag;
    }

    /**
     * 输入两个整数m和n，计算需要改变m的二进制表示中的多少位才能得到n。比如：10的二进制位1010，13的二进制位1101，1010改变3位才能变成1101。
     */
    private static int countChangeNumber(int m, int n) {
        int count = 0;
        int r = m ^ n;
        while (r != 0) {
            count++;
            r = (r - 1) & r;
        }
        log.info("{}:{}:{}", m, n, count);
        return count;
    }
}
