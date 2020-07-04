package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ03FindOutDup {

    /**
     * 要点：交换
     */
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 2, 4};
        log.info("{}", array);
        log.info("{}", findDup(array));
    }

    /**
     * 在长度n的数组中查找0-n-1的重复数字
     */
    private static int findDup(int[] array) {
        if (Objects.isNull(array) || array.length == 0) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (i == array[i]) {
                continue;
            }

            int currentIdx = i;
            while (array[currentIdx] != currentIdx) {
                int currentVal = array[i];
                int targetVal = array[currentVal];
                if (targetVal == currentVal) {
                    return currentVal;
                } else {
                    array[currentVal] = currentVal;
                    array[currentIdx] = targetVal;
                    log.info("{}, currentIdx:{}, currentVal:{}", array, currentIdx, array[currentIdx]);
                }
            }
        }

        return -1;
    }

}
