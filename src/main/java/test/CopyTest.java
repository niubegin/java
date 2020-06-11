package test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class CopyTest {

    public static void main(String[] args) {
        CopyTest copyTest = new CopyTest();
        copyTest.test();
    }

    /**
     * BeanUtils.copyProperties不支持对象的深拷贝
     */
    private void test() {
        OuterClass initOuterClass = new OuterClass();
        initOuterClass.setName("outerClass");
        initOuterClass.setInnerClass(new InnerClass());
        initOuterClass.getInnerClass().setName("init");
        OuterClass copiedOuterClass = new OuterClass();
        BeanUtils.copyProperties(initOuterClass, copiedOuterClass);
        log.info("init outerClass name:{}, innerClass name:{}",
            initOuterClass.getName(), initOuterClass.getInnerClass().getName());
        //改变init对象
        initOuterClass.setName("changedOuterClass");
        initOuterClass.getInnerClass().setName("changedInnerClass");
        //恢复init对象
        BeanUtils.copyProperties(copiedOuterClass, initOuterClass);
        //期望是init，结果是changed，说明不是深度拷贝
        log.info("recovered outerClass name:{}, innerClass name:{}",
            initOuterClass.getName(), initOuterClass.getInnerClass().getName());
    }

    @Data
    private class OuterClass {

        private String name;

        private InnerClass innerClass;
    }

    @Data
    private class InnerClass {

        private String name;
    }
}
