package cn.net.bhe.basics.reflect.delegate.jdk;

import static cn.net.bhe.utils.main.PrintUtils.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 只能代理接口，也就是目标对象至少实现一个接口。jdk动态代理之所以只能代理接口是因为代理类本身已经
 * extends了Proxy，而java是不允许多重继承的，但是允许实现多个接口。底层实现是根据[目标对象、公共
 * 接口、InvocationHandler的自定义实现]动态生成字节码，再生成class文件，编译并实例化，此时代理类
 * 已经具备相关接口，但是调用时会会转为对InvocationHandler自定义实现的调用。
 * 
 * 代理类反编译大致如下：
 * public final class $Proxy4 extends Proxy  implements UserService {
 *     private static Method m1;
 *     private static Method m3;
 *     private static Method m2;
 *     private static Method m0;
 *     // 代理类通过反射保存了相关方法
 *     static {
 *         try {
 *             m1 =  Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
 *             m3 =  Class.forName("proxy.jdk.UserService").getMethod("getUserByName",  Class.forName("java.lang.String"));
 *             m2 =  Class.forName("java.lang.Object").getMethod("toString");
 *             m0 =  Class.forName("java.lang.Object").getMethod("hashCode");
 *         } catch (NoSuchMethodException var2) {
 *             throw new  NoSuchMethodError(var2.getMessage());
 *         } catch (ClassNotFoundException var3) {
 *             throw new  NoClassDefFoundError(var3.getMessage());
 *         }
 *     }
 *     // 父类持有InvocationHandler的自定义实现
 *     public $Proxy4(InvocationHandler var1)  throws  {
 *         super(var1);
 *     }
 *     // 被代理的方法
 *     public final String getUserByName(String  var1) throws  {
 *         try {
 *             // 关键部分，invoke即为InvocationHandler的自定义实现
 *             return (String)super.h.invoke(this,  m3, new Object[]{var1});
 *         } catch (RuntimeException | Error var3)  {
 *             throw var3;
 *         } catch (Throwable var4) {
 *             throw new  UndeclaredThrowableException(var4);
 *         }
 *     }
 *         ......
 * }
 */
public class __App__ {
    
    public static void main(String[] args) {
        Hello targetObj = new HelloImp();
        Hello hello = (Hello) Proxy.newProxyInstance(
                targetObj.getClass().getClassLoader(),  // 目标类类加载器
                targetObj.getClass().getInterfaces(),   // 目标类实现的接口
                new LogInvocationHandler(targetObj));   // 回掉类
        
        hello.sayHello("I love you");
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

    /**
     * @param proxy         生成的代理实例
     * @param method        原方法
     * @param args          原参数
     * @return
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        pln("before log...");
        Object ret = method.invoke(target, args);
        pln("after log...");
        return ret;
    }
}

/**
 * 目标，必需实现至少一个接口
 */
class HelloImp implements Hello {
    @Override
    public String sayHello(String str) {
        str = "Hello ".concat(str).concat("!");
        pln(str);
        return  str;
    }
}

interface Hello {
    String sayHello(String str);
}