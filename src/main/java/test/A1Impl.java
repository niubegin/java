package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A1Impl implements InterfaceA{

    @Override
    public void test() {
        log.info("A1");
    }
}
