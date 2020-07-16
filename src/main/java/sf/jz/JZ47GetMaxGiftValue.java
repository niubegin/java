package sf.jz;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ47GetMaxGiftValue {

    /**
     * 要点：递归；避免递归容易出现的重复计算问题；
     */
    public static void main(String[] args) {
        int[][] giftValueArr = {{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        log.info("{}", get(giftValueArr, 0, 0, 0));
        //字符串连接计算测试
        log.info("{}", 1 + 1 + "" + 2);
        log.info("{}", get(giftValueArr, 0, 0, 0, new HashMap<>()));
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

    /**
     * 使用空间优化，性能好的代码就复杂
     */
    private static int get(int[][] giftValueArr, int rowIndex, int columnIndex, int sum, Map<String, Integer> sumMap) {
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
        int rightSum = 0;
        if (Objects.isNull(sumMap.get(rowIndex + "" + columnIndex))) {
            sumMap.put(rowIndex + "" + columnIndex, get(giftValueArr, rowIndex, columnIndex + 1, sum));
        }

        rightSum = sumMap.get(rowIndex + "" + columnIndex);
        //向下走
        int downSum = 0;
        if (Objects.isNull(sumMap.get(rowIndex + 1 + "" + columnIndex))) {
            sumMap.put(rowIndex + 1 + "" + columnIndex, get(giftValueArr, rowIndex + 1, columnIndex, sum));
        }

        downSum = sumMap.get(rowIndex + 1 + "" + columnIndex);
        //取向右和向下的最大值返回
        return rightSum > downSum ? rightSum : downSum;
    }
}
