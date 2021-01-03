package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jTest {

    static {
        // 这个例子，通过 javap -c反编译，可以发现，@Slf4j的实现，是将 Logger 放到了 static 块内实现的
        log.info("static init");
    }

    public static void main(String[] args) {
        log.info("main");
    }
}
