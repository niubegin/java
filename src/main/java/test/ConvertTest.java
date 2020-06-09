package test;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ConvertTest {

    public static void main(String[] args) {
        Long lg = new Long(10L);
        Map<String, Object> map = new HashMap<>(10);
        map.put("1", lg);
        System.out.println(StringUtils.isBlank(map.get("1").toString()));
    }
}
