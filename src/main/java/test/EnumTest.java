package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumTest {

    public static void main(String[] args) {
        USE_TYPE use_type = USE_TYPE.TYPE1;
        log.info("{}", USE_TYPE.TYPE1.equals(use_type));
        log.info("{},{}", USE_TYPE.TYPE1.name(), USE_TYPE.TYPE1.name().equals(use_type));
    }
}
