package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TypeCheck {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        log.info("{}", map instanceof Map);
        List<Map<String, String>> list = new ArrayList<>();
        log.info("{}", list instanceof List);
    }
}
