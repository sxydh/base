package cn.net.bhe.spring.framework.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void pointcut() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

    @Before(value = "pointcut()")
    public void beforeTest() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

    @After(value = "pointcut()")
    public void afterTest() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

}
