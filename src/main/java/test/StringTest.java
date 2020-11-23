package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class StringTest {

    public static void main(String[] args) {
        log.info("{}", "01234".substring(0, 4));
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("key", null);
        list.add(map);
        Object obj = list;
        List<Map<String, String>> list2 = (List<Map<String, String>>) obj;
        log.info("{}", list2.get(0).get("key"));

        //取值>=1
        paddingZeroAtLeft(0);
        paddingZeroAtLeft(1);
        paddingZeroAtLeft(9);
        paddingZeroAtLeft(98);
        paddingZeroAtLeft(99);
        paddingZeroAtLeft(100);
        paddingZeroAtLeft(101);
        Integer len = 1;
        log.info("{}", StringUtils.join("PRE", StringUtils.repeat('_', len)));
        log.info("{}", StringUtils.join("PRE", StringUtils.repeat('_', 4)));
        log.info("{}", getSerialNumFromContainerCode("aaaa999999999aaaa", "aaaa_________aaaa", 9));
        log.info("{}", getSerialNumFromContainerCode("aaaa9999999990", "aaaa__________aaaa", 10));
        log.info("{}", getSerialNumFromContainerCode("aaaa1234", "aaaa____", 4));
        log.info("{}", getSerialNumFromContainerCode("1234", "____", 4));
        log.info("{}", getSerialNumFromContainerCode("abcd", "____", 4));
        log.info("{}", Integer.MAX_VALUE);
        log.info("{}", Integer.parseInt("00001") + 1);
//        orderStrings();
    }

    private static int getSerialNumFromContainerCode(String containerCode, String containerCodeLike,
        int serialNumLength) {
        try {
            char[] containerCodeChars = containerCode.toCharArray();
            char[] containerCodeLikeChars = containerCodeLike.toCharArray();
            char[] serialNumChars = new char[serialNumLength];
            int serialNumCharsIndex = 0;
            for (int index = 0; index < containerCodeChars.length; index++) {
                if (containerCodeChars[index] != containerCodeLikeChars[index]) {
                    serialNumChars[serialNumCharsIndex] = containerCodeChars[index];
                    serialNumCharsIndex++;
                    if (serialNumCharsIndex == serialNumLength) {
                        break;
                    }
                }
            }

            log.info("容器号：{}，容器号匹配串：{}，解析到序列号：{}", containerCode, containerCodeLike, serialNumChars);
            return Integer.parseInt(String.valueOf(serialNumChars));
        } catch (Exception e) {
            log.warn("容器号：{}，容器号前缀：{}，获取序列号异常：", containerCode, containerCodeLike, e);
            return 0;
        }
    }

    private static void paddingZeroAtLeft(int num) {
        int t = 999999999;
        int serialNumLength = 9;
        int max = (int) Math.pow(10, serialNumLength) - 1;
        //取值范围0-98
        int mod = (num % max) + 1;
        String result = String.format("%0" + serialNumLength + "d", mod);
        log.info("max:{},result:{},num:{}", max, result, num);
    }

    private static void orderStrings() {
        try {
            String autoBindingContainerGeneratorOrderRule = StringUtils.defaultString("43321", "123");
            String[] itemArray = {"dynamicContent", "fixContent", "serialNum"};
            StringBuilder sb = new StringBuilder();
            //根据参数指定的排序规则拼接容器号
            int index = 0;
            for (char item : autoBindingContainerGeneratorOrderRule.toCharArray()) {
                index = Integer.parseInt(String.valueOf(item)) - 1;
                if (index >= itemArray.length) {
                    log.warn("排序索引超出范围，index：{}", index + 1);
                    continue;
                }

                sb.append(itemArray[index + 1]);
            }

            log.info("{}", sb.toString());
        } catch (Exception e) {
            log.warn("异常：", e);
            log.info("{}", "默认");
        }
    }
}
