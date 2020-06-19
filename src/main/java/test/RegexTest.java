package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegexTest {

    /**
     * 字符串数组log输出仅第一个元素，数字的会输出所有
     */
    public static void main(String[] args) {
        String[] arr = "a$b$c".split("\\$");
        log.info("{}", arr);
        int[] nums = {1, 2, 3};
        log.info("{}", nums);
        String[] strs = {"1", "2", "3"};
        log.info("{}", strs);
    }
}
