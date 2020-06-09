package proxy;

import proxy.cglib.UserManagerCglib;
import proxy.jdk.LogHandler;
import proxy.user.UserManager;
import proxy.user.UserManagerImpl;

public class Client {

    /**
     * https://www.cnblogs.com/maohuidong/p/7992894.html 可以看到，我们可以通过LogHandler动态代理不同类型的对象，如果我们把对外的接口都通过动态代理来实现，
     * 那么所有的函数调用最终都会经过invoke函数做转发，因此我们就可以在这里做一些自己想做的操作， 比如日志系统、事务、拦截器、权限控制等。这也就是AOP(面向切面编程)的基本原理。 AOP（Aspect Oriented
     * Programming）：将日志记录，性能统计，安全控制，事务处理，异常处理等代码从业务逻辑代码中划分出来， 通过对这些行为的分离，我们希望可以将它们独立到非指导业务逻辑的方法中，进而改变这些行为的时候不影响业务逻辑的代码---解耦。
     * 针对如上的示例解释： 我们来看上面的UserManagerImplProxy类，它的两个方法System.out.println("start-->addUser()")和System.out.println("success-->addUser()")，
     * 这是做核心动作之前和之后的两个截取段，正是这两个截取段，却是我们AOP的基础，在OOP里， System.out.println("start-->addUser()")、核心动作、System.out.println("success-->addUser()")
     * 这三个动作在多个类里始终在一起，但他们所要完成的逻辑却是不同的， 如System.out.println("start-->addUser()")里做的可能是权限的判断，在所有类中它都是做权限判断，而在每个类里核心动作却各不相同，
     * System.out.println("success-->addUser()")可能做的是日志，在所有类里它都做日志。 正是因为在所有的类里，核心代码之前的操作和核心代码之后的操作都做的是同样的逻辑，因此我们需要将它们提取出来，单独分析，设计和编码，
     * 这就是我们的AOP思想。一句话说，AOP只是在对OOP的基础上进行进一步抽象，使我们的类的职责更加单一。 动态代理优点： 动态代理与静态代理相比较，最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理（InvocationHandler.invoke）。
     * 这样，在接口方法数量比较多的时候，我们可以进行灵活处理，而不需要像静态代理那样每一个方法进行中转。 而且动态代理的应用使我们的类职责更加单一，复用性更强。 总结：
     * 其实所谓代理，就是一个人或者一个机构代表另一个人或者另一个机构采取行动。在一些情况下，一个客户不想或者不能够直接引用一个对象， 而代理对象可以在客户端和目标对象之前起到中介的作用。
     * 代理对象就是把被代理对象包装一层，在其内部做一些额外的工作，比如用户需要上facebook,而普通网络无法直接访问，网络代理帮助用户先翻墙， 然后再访问facebook。这就是代理的作用了。
     * 纵观静态代理与动态代理，它们都能实现相同的功能，而我们看从静态代理到动态代理的这个过程，我们会发现其实动态代理只是对类做了进一步抽象和封装， 使其复用性和易用性得到进一步提升而这不仅仅符合了面向对象的设计理念，其中还有AOP的身影，这也提供给我们对类抽象的一种参考。
     * 关于动态代理与AOP的关系，个人觉得AOP是一种思想，而动态代理是一种AOP思想的实现！
     */
    public static void main(String[] args) {
        callJdkProxy();
        callCglibProxy();
    }

    /**
     * jdk动态代理
     */
    private static void callJdkProxy() {
        LogHandler logHandler = new LogHandler();
        UserManager userManager = (UserManager) logHandler.newProxyInstance(new UserManagerImpl());
        userManager.addUser("1", "张三");
        // 可以代理任何实现了接口的类
        Comparable<Integer> num1 = (Comparable<Integer>) logHandler.newProxyInstance(new Integer(1));
        num1.compareTo(2);
        // 隐式类型转换
        Comparable<Integer> num2 = (Comparable<Integer>) logHandler.newProxyInstance(2);
        num2.compareTo(2);
    }

    /**
     * cglib动态代理 https://www.jianshu.com/p/9a61af393e41
     */
    private static void callCglibProxy() {
        // cglib进行动态代理
        UserManagerCglib cglib = new UserManagerCglib();
        UserManagerImpl userManagerByCglib = (UserManagerImpl) cglib.getInstance(new UserManagerImpl());
        userManagerByCglib.addUser("2", "李四");
        userManagerByCglib.delUser("2");
    }
}
