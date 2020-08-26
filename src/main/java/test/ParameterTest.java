package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParameterTest {

    public static void main(String[] args) {
        int[] arr = new int[2];
        get(arr);
        log.info("{}", arr);
        get2(arr);
        log.info("{}", arr);
    }

    private static void get(int[] arr) {
        arr[0] = 1;
        arr[1] = 2;
    }

    private static void get2(int[] arr) {
        // 参数修改后，外部调用看不到变化
        arr = new int[2];
        arr[0] = 3;
        arr[1] = 4;
    }

}
