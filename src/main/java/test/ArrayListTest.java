package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

        List<String> hasEmptyStrList = new ArrayList<>();
        hasEmptyStrList.add("a");
        hasEmptyStrList.add("");
        hasEmptyStrList.add("c");
        hasEmptyStrList.add(" ");
        hasEmptyStrList.add("d");
        hasEmptyStrList.add(" ");
        log.info("{}", hasEmptyStrList);
        hasEmptyStrList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("{}", hasEmptyStrList);
        hasEmptyStrList = hasEmptyStrList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
        log.info("{}", hasEmptyStrList);
    }
}
