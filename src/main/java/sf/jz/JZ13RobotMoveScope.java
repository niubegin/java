package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ13RobotMoveScope {

    public static void main(String[] args) {
        int maxX = 100;
        int maxY = 100;
        int[] mark = new int[maxX * maxY];
        /*
        travelInMatrix(maxX, maxY, 0, 0, 18, mark);
        int sum = 0;
        for (int m : mark) {
            sum++;
        }
        log.info("{}", sum);
        */
        log.info("{}", countInMatrix(maxX, maxY, 0, 0, 20, mark));
        log.info("{},{},{}", getNum(0), canMove(35, 37, 18), canMove(35, 38, 18));
    }

    /**
     * 正确的思路
     */
    private static int countInMatrix(int maxX, int maxY, int x, int y, int k, int[] mark) {
        int count = 0;
        //当前点是否进行计数
        if (checkCount(maxX, maxY, x, y, k, mark)) {
            //标记
            mark[x * y + y] = 1;
            count = 1 + countInMatrix(maxX, maxY, x, y + 1, k, mark) +
                countInMatrix(maxX, maxY, x, y - 1, k, mark) +
                countInMatrix(maxX, maxY, x - 1, y, k, mark) +
                countInMatrix(maxX, maxY, x + 1, y, k, mark);
        }
        log.info("x:{},y:{},count:{}", x, y, count);
        return count;
    }

    private static boolean checkCount(int maxX, int maxY, int x, int y, int k, int[] mark) {
        //越界
        if (x < 0 || y < 0 || x >= maxX || y >= maxY) {
            return false;
        }
        //可以走且没有标记
        if (canMove(x, y, k) && !hasMark(x, y, mark)) {
            return true;
        }
        return false;
    }

    private static boolean hasMark(int x, int y, int[] mark) {
        return mark[x * y + y] == 1;
    }

    /**
     * 开始错误的思路，递归没有收敛，导致栈溢出
     */
    private static void travelInMatrix(int maxX, int maxY, int x, int y, int k, int[] mark) {
        log.info("x:{},y:{}", x, y);
        //越界返回
        if (x < 0 || y < 0 || x >= maxX || y >= maxY) {
            return;
        }
        //当前点可以走
        if (canMove(x, y, k)) {
            //标记
            mark[x * y + y] = 1;
            //尝试四个方向是否可行
            //右
            if (y + 1 < maxX) {
                travelInMatrix(maxX, maxY, x, y + 1, k, mark);
            }
            //左
            if (y >= 1) {
                travelInMatrix(maxX, maxY, x, y - 1, k, mark);
            }
            //下
            if (x + 1 < maxX) {
                travelInMatrix(maxX, maxY, x + 1, y, k, mark);
            }
            //上
            if (x >= 1) {
                travelInMatrix(maxX, maxY, x - 1, y, k, mark);
            }
        } else {

        }
    }

    private static boolean canMove(int x, int y, int k) {
        int sum = getNum(x) + getNum(y);
        return sum <= k;
    }

    private static int getNum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }
}
