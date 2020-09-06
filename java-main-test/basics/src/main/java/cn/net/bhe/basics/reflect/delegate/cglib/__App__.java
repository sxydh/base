package cn.net.bhe.basics.reflect.delegate.cglib;

import static cn.net.bhe.utils.main.PrintUtils.*;
import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * ▪ CGLIB(Code Generation Library)是一个基于ASM的字节码生成库，它允许我们在运行时对字节码进行修改和动态
 * 生成。
 * ▪ CGLIB通过继承方式实现代理，所以目标不能是final的。
 * 
 * 代理类反编译大致如下：
 * public class UserDaoImpl$$EnhancerByCGLIB$$4a4f3531 extends UserDaoImpl {
 *     ...
 *     public final void updateName() {
 *         MethodInterceptor var10000 = this.CGLIB$CALLBACK_0;
 *         if (var10000 == null) {
 *             CGLIB$BIND_CALLBACKS(this);
 *             var10000 = this.CGLIB$CALLBACK_0;
 *         }
 *         if (var10000 != null) {
 *             var10000.intercept(this, CGLIB$updateName$0$Method, CGLIB$emptyArgs, CGLIB$updateName$0$Proxy);
 *         } else {
 *             super.updateName();
 *         }
 *     }
 *     ...
 * }
 */
public class __App__ {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Hello.class);
        enhancer.setCallback(new MyMethodInterceptor()); // 设置回掉
        Hello hello = (Hello)enhancer.create();
        
        hello.sayHello("I love you");
    }
    
}

class MyMethodInterceptor implements MethodInterceptor {
    /**
     * @param proxy         生成的代理实例
     * @param method        原方法
     * @param args          原参数
     * @param methodProxy   
     * @return
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        pln("before log...");
        Object ret = methodProxy.invokeSuper(proxy, args); // 调用super method，即原方法
        pln("after log...");
        return ret;
    }
}
