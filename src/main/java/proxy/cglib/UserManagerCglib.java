package proxy.cglib;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class UserManagerCglib implements MethodInterceptor {

    // 目标对象
    private Object target;

    /**
     * 创建代理对象
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 实现MethodInterceptor接口中重写的方法
     *
     * 回调方法
     *
     * @param object 目标对象
     * @param method 要执行的方法
     * @param args 方法的参数数组
     * @param proxy 代理类
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("事务开始。。。");
        Object result = proxy.invokeSuper(object, args);
        System.out.println("事务结束。。。");
        return result;
    }

}
