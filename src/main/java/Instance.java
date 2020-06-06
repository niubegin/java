public class Instance {

    /**
     * 私有化构造方法
     */
    private Instance(){}

    /**
     * 虚拟机规范严格规定了有且只有以下 5 中情况必须立即对类进行初始化（加载，验证，准备自然在初始化之前进行）：
     *
     * a.遇到 new、getstatic、putstatic或invokestatic这 4 条字节码指令时，如果类未进行过初始化，
     * 那么需首先触发类的初始化。分别对应的场景为：
     *  1、使用 new 关键字实例化对象时；
     *  2、读取类的静态变量时（被 final 修饰，已在编译期把结果放入常量池的静态字段除外）；
     *  3、设置类的静态变量时；
     *  4、调用一个类的静态方法时。
     * b.使用反射对类进行调用时。
     * c.初始化一个类时，如果其父类还未初始化，那么会先触发其父类的初始化。
     * d.当虚拟机启动时，被指定为需要执行的那个主类（main() 方法所在的类），虚拟机需要先初始化。
     * e.JDK 1.7 动态语言支持，详见《深入理解 Java 虚拟机》。
     */
    private static class InstanceHolder{
        //注意不能加final，会导致提前初始化InstanceHolder类
        //基于volatile的双重检查锁定的方案有一个额外的优势：
        //除了可以对静态字段实现延迟初始化外，还可以对实例字段实现延迟初始化。
        //本方案只支持静态字段
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;
    }

    public static void main(String[] args) {
        Instance instance = Instance.getInstance();
        instance = new Instance();
    }
}
