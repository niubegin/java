package test;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortTest {

    public static void main(String[] args) {
        String[] singleStationIdSplit = "2,1,3".split(",");
        Arrays.sort(singleStationIdSplit);
        log.info("{}", singleStationIdSplit);
    }
}
