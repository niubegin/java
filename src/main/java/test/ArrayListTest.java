package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ArrayListTest {

    public static void main(String[] args) {
        //new ArrayList<>(null);
        List<String> list = new LinkedList<>();
        List<String> toAddList = new ArrayList<>();
        toAddList.add("1");
        List<String> res = toAddList.stream().filter(item -> item.equals("2")).collect(Collectors.toList());
        list.addAll(res);
        List<String> nullList = null;
//        nullList.stream().filter(item -> item.equals("2")).collect(Collectors.toList());
//        list.addAll(nullList);

        List<String> hasEmptyStrList;
        hasEmptyStrList = new CopyOnWriteArrayList<>();
//        hasEmptyStrList = new ArrayList<>();
        hasEmptyStrList.add("a");
        hasEmptyStrList.add("");
        hasEmptyStrList.add("c");
        hasEmptyStrList.add(" ");
        hasEmptyStrList.add("d");
        hasEmptyStrList.add(" ");
        hasEmptyStrList.add(null);

        log.info("{}", hasEmptyStrList.stream().anyMatch(item -> StringUtils.equals(item, "a")));

        remove2(hasEmptyStrList, "d");
        remove2(hasEmptyStrList, null);
        //把remove的还回去
        hasEmptyStrList.add("d");
        hasEmptyStrList.add(null);
        remove(hasEmptyStrList, "d");
        remove(hasEmptyStrList, null);
        //把remove的还回去
        hasEmptyStrList.add("d");
        hasEmptyStrList.add(null);

        log.info("{}", hasEmptyStrList);
        hasEmptyStrList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("{}", hasEmptyStrList);
        hasEmptyStrList = hasEmptyStrList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("{}", hasEmptyStrList);
    }

    /**
     * CopyOnWriteArrayList 该方式删除会报错java.lang.UnsupportedOperationException
     */
    private static void remove(List<String> list, String target) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String cur = iterator.next();
            if (StringUtils.equals(cur, target)) {
                iterator.remove();
                log.info("class: {}, remove {}", list.getClass(), cur);
                return;
            }
        }
    }

    /**
     * 该方式支持CopyOnWriteArrayList，也支持ArrayList
     */
    private static void remove2(List<String> list, String target) {
        for (String cur : list) {
            if (StringUtils.equals(cur, target)) {
                list.remove(cur);
                log.info("class: {}, remove {}", list.getClass(), cur);
                return;
            }
        }
    }

}
