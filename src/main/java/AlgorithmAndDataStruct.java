import java.util.Arrays;
import org.junit.Test;

public class AlgorithmAndDataStruct {
    /**
     * https://www.tinymind.cn/articles/3759
     * 算法和编程面试题精选TOP50！(附代码+解题思路+答案)
     *
     */

    /**
     * 数组
     */
    /**
     * ▌1.给定一个 1-100 的整数数组，请找到其中缺少的数字。 思路1： a.从小到大排序 b.查找缺少的数字：两个指针A和B，A指向1-100的外层for循环，B指向数组当前下标
     * 》如果A==B，则A++，B++：获取下一个不同的值，避免重复值 》如果A<B，则输出A，A++ 思路2： a.从小到大排序 b.遍历整数数组： 》下标==0时，输出1至当前下标值的整数；
     * 》下标>0时，输出上一个值到当前值的整数； 》下标==最后时，输出当前值到100的整数；
     */
    @Test
    public void a10() {
        int[] data = {3, 5, 3, 6};
        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        int dataLength = data.length;
        boolean findEnd = false;
        for (int a = 1, b = 0; a <= 100; a++) {
            //B指向结尾，输出A后面的所有值
            if (findEnd) {
                System.out.println(a);
            } else {
                //AB指向位置相等
                if (a == data[b]) {
                    //B指向后面一个不相等的位置
                    while (a == data[b]) {
                        if (b < dataLength - 1) {
                            b++;
                        } else {
                            findEnd = true;
                            break;
                        }
                    }
                } else if (a < data[b]) {
                    System.out.println(a);
                }
            }
        }
        //思路2
        for (int b = 0; b < dataLength; b++) {
            if (0 == b) {
                //第一个元素
                outInt(0, data[b]);
            } else {
                //第二至最后的元素
                outInt(data[b - 1], data[b]);
            }

            if (b == dataLength - 1) {
                //最后的元素
                outInt(data[b], 101);
            }
        }
    }

    private void outInt(int start, int end) {
        //如果间距不足2，则不输出
        if (end - start < 2) {
            return;
        }
        //输出start到end间整数
        for (int a = start + 1; a < end; a++) {
            System.out.println(a);
        }
    }
}
