package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ51GetInversePairs {

    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 4};
        get(arr);
    }

    /**
     * {7,5,6,4}，时间复杂度：O(n)
     */
    private static void get(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    log.info("{}, {}", arr[i], arr[j]);
                }
            }
        }
    }
}
