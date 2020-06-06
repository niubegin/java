package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListTest {
    public static void main(String[] args) {
        //new ArrayList<>(null);
        List<String> list = new LinkedList<>();
        List<String> toAddList = new ArrayList<>();
        toAddList.add("1");
        List<String> res = toAddList.stream().filter(item->item.equals("2")).collect(Collectors.toList());
        list.addAll(res);
        List<String> nullList = null;
        nullList.stream().filter(item->item.equals("2")).collect(Collectors.toList());
        list.addAll(nullList);
    }
}
