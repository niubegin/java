package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONTest {

    /**
     * 正确的JSON是这样的：属性名必须用双引号包裹
     */
    public static void main(String[] args) {
        JSONObject jsonObject = null;
        String text = "{\"name\":\"im\\\"ooc\"}";
        try {
            jsonObject = JSON.parseObject(text);
        } catch (Exception e) {
            log.warn("parseObject exception {}", text, e);
            jsonObject = null;
        }
        log.info("{}", jsonObject);
    }
}
