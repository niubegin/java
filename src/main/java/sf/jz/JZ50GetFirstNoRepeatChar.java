package sf.jz;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ50GetFirstNoRepeatChar {

    public static void main(String[] args) {
        log.info("{}", get("abccae"));
        log.info("{}", get("aeccab"));
    }

    /**
     * 查找第一个不重复的字符，如：abccae，返回b
     */
    private static char get(String str) {
        char[] chars = str.toCharArray();
        //使用插入有序的map；使用LinkedHashMap，不能使用TreeMap
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (Entry<Character, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }

        return '\0';
    }
}
