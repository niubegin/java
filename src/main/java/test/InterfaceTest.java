package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterfaceTest {

    public static void main(String[] args) {
        InterfaceA interfaceA = new A1Impl();
        InterfaceA interfaceA1 = new A2Impl();
        HashMap<String, Set<InterfaceA>> map = new HashMap<>();
        log.info("{}", interfaceA.getClass().toString());
        map.put(interfaceA.getClass().toString(), null);
    }
}
