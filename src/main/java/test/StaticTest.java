package test;

public class StaticTest {

    //内部类的静态属性
    class OuterClass {

        public int age = 20;

        class InnerClass {

            //static int a = 100; //这样写是不合法的 compile error：java: 此处不允许使用修饰符static
            //static void f() { } // compile error：java: 此处不允许使用修饰符static
            static final int b = 0;//这样写是合法的
        }
    }
}
