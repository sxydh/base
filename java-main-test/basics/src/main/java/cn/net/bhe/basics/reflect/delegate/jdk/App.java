package cn.net.bhe.basics.reflect.delegate.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy实例是运行时产生的
 * Proxy实例实现了目标实现的接口
 * Proxy实例方法调用都会转发给InvocationHandler.invoke
 */
public class App {
    
    public static void main(String[] args) {
        Hello targetObj = new HelloImp();
        Hello hello = (Hello) Proxy.newProxyInstance(
                targetObj.getClass().getClassLoader(), 
                targetObj.getClass().getInterfaces(), 
                new LogInvocationHandler(targetObj));
        System.out.println(hello.sayHello("I love you"));
    }

}

/**
 * 代理
 */
class LogInvocationHandler implements InvocationHandler{
    
    Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("log...");
        return method.invoke(target, args);
    }
}

/**
 * 目标，必需实现至少一个接口
 */
class HelloImp implements Hello {
    @Override
    public String sayHello(String str) {
        return "Hello " + str + "!";
    }
}

interface Hello {
    String sayHello(String str);
}