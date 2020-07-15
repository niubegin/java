package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ47GetMaxGiftValue {

    /**
     * 要点：递归
     */
    public static void main(String[] args) {
        int[][] giftValueArr = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        log.info("{}", get(giftValueArr, 0, 0, 0));
    }

    /**
     * 递归：先写递归的退出逻辑；再写当前参数的递归调用逻辑；
     */
    private static int get(int[][] giftValueArr, int rowIndex, int columnIndex, int sum) {
        if (rowIndex >= giftValueArr.length || columnIndex >= giftValueArr[0].length) {
            return 0;
        }

        //加上当前坐标的值
        sum += giftValueArr[rowIndex][columnIndex];
        //到达终点，返回和
        if (rowIndex == giftValueArr.length - 1 && columnIndex == giftValueArr[0].length - 1) {
            return sum;
        }

        //向右走
        int rightSum = get(giftValueArr, rowIndex, columnIndex + 1, sum);
        //向下走
        int downSum = get(giftValueArr, rowIndex + 1, columnIndex, sum);
        //取向右和向下的最大值返回
        return rightSum > downSum ? rightSum : downSum;
    }
}
