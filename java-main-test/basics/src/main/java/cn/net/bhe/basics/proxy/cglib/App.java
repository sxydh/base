package cn.net.bhe.basics.proxy.cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * CGLIB(Code Generation Library)是一个基于ASM的字节码生成库
 * 它允许我们在运行时对字节码进行修改和动态生成
 * CGLIB通过继承方式实现代理
 * 目标不能是final的
 */
public class App {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Hello hello = (Hello)enhancer.create();
        System.out.println(hello.sayHello("I love you"));
    }
    
}

class MyMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("log...");
        return proxy.invokeSuper(obj, args);
    }
}
