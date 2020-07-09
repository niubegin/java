package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectTest {

    public static void main(String[] args) {
        ChildObject childObject1 = new ChildObject();
        ParentObject parentObject1 = childObject1;
        try {
            ParentObject parentObject = new ParentObject();
            //java.lang.ClassCastException 父类转子类报运行时错误
            ChildObject childObject = (ChildObject) parentObject;
        } catch (Exception e) {
            log.warn("{}", e);
        }

        ParentObject parentObject2 = new ChildObject();
        if (parentObject2 instanceof ChildObject) {
            ChildObject childObject2 = (ChildObject) parentObject2;
            log.info("done");
        }
    }

}
