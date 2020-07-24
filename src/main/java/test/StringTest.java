package test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class StringTest {

    public static void main(String[] args) {
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
