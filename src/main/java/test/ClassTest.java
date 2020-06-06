package test;

import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import test.ClassTest.InnerPublicClass;

/**
 * 内部类： https://www.cnblogs.com/dolphin0520/p/3811445.html
 */
@Slf4j
public class ClassTest {

    public static void main(String[] args) {
        URL resource = ClassTest.class.getResource("");
        URL resource2 = TestCaller.class.getResource("");
        URL resource3 = InnerClass.class.getResource("");
        log.info("ClassTest resource:{},Test resource:{},InnerClass resource:{},", resource, resource2, resource3);
    }

    private class InnerClass {

    }

    public class InnerPublicClass {

        public void called() {
            log.info("inner public class");
        }
    }

    public InnerPublicClass getInnerPublicClassInstance() {
        return new InnerPublicClass();
    }
}

//一个.java文件里面可以有多个类，public的只能有一个
class TestCaller {

    public void call() {
        //无法调用
        //InnerPublicClass innerPublicClass = new InnerPublicClass();
        //innerPublicClass.called();
        //第一种方法
        ClassTest classTest = new ClassTest();
        //必须通过ClassTest对象来创建，说明new是有上下文的
        InnerPublicClass innerPublicClass = classTest.new InnerPublicClass();
        innerPublicClass.called();
        //第二种方法
        InnerPublicClass innerPublicClass1 = classTest.getInnerPublicClassInstance();
        innerPublicClass1.called();
    }
}
