package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SetTest {

    public static void main(String[] args) {
        List<Long> ids1 = new ArrayList<>();
        ids1.add(1L);
        ids1.add(2L);
        List<Long> ids2 = new ArrayList<>();
        ids2.add(1L);
        ids2.add(3L);
        Set<Long> ids = ids1.stream().collect(Collectors.toSet());
        ids.addAll(ids2);
        log.info("{}", ids);
    }

}
