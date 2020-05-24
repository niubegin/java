package sf.jz;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ04FindInArray {
    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4}, {6, 7, 8, 9}, {11, 12, 13, 14}};
        log.info("{}", find(10, array));
        log.info("{}", find(12, array));
        log.info("{}", find(15, array));
    }

    /**
     * 比如从下面的数组找5
     * 1234
     * 6789
     *
     * @param target
     * @param array
     * @return
     */
    private static boolean find(int target, int[][] array) {
        int rows = array.length;
        if (rows == 0) {
            return false;
        }

        int cols = array[0].length;
        //自己的版本，等价于下面的for (int row = 0, col = cols - 1; row < rows && col >= 0; ) {
        int row = 0, col = cols - 1;
        while (row < rows - 1 && col > 0) {
            int val = array[row][col];
            if (val == target) {
                return true;
            }

            if (val < target) {
                row++;
            }

            if (val > target) {
                col--;
            }
        }

        return false;
    }
}
