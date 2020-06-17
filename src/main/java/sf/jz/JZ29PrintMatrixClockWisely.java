package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ29PrintMatrixClockWisely {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        print(matrix);
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        print(matrix2);
    }

    /**
     * 好难啊，自己实现的版本，通过计数结束循环，最后一圈，特殊处理1个节点的情况
     */
    private static void print(int[][] matrix) {
        if (Objects.isNull(matrix)) {
            return;
        }
        int height = matrix.length;
        if (height == 0) {
            return;
        }
        int width = matrix[0].length;
        //终止条件
        int total = width * height;
        int i = 0;
        int j = 0;
        int startI = 0;
        int startJ = 0;
        int endI = height - 1;
        int endJ = width - 1;
        int circleNum = 0;
        while (total > 0) {
            i = startI;
            j = startJ - 1;
            circleNum = 2 * width + 2 * (height - 2);
            if (width == 1 && height == 1) {
                circleNum = 1;
            }
            while (j < endJ && circleNum > 0) {
                j++;
                log.info("{}", matrix[i][j]);
                circleNum--;
                total--;
            }
            while (i < endI && circleNum > 0) {
                i++;
                log.info("{}", matrix[i][j]);
                circleNum--;
                total--;
            }
            while (j > startJ && circleNum > 0) {
                j--;
                log.info("{}", matrix[i][j]);
                circleNum--;
                total--;
            }
            while (i > startI && circleNum > 0) {
                i--;
                log.info("{}", matrix[i][j]);
                circleNum--;
                total--;
            }
            startI = i;
            startJ = j + 1;
            endI = width - 1;
            endJ = height - 1;
            width -= 2;
            height -= 2;
        }
    }
}
