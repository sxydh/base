package cn.net.bhe.springframework.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public void a() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
        System.out.println(this);
        // 如果用this.b()的话，增强方法是无法执行的，因为this指向被代理对象，而不是代理，调用b()时无法进入代理的invoke方法，而增强方法的执行就依赖于invoke
        // 要获取当前对象的代理必需配置<aop:aspectj-autoproxy expose-proxy="true"/>
        ((MyBean) AopContext.currentProxy()).b();
    }
    
    public void b() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

}
