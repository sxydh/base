package cn.net.bhe.springframework.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.*(..))")
    public void pointcut() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod + " " + joinPoint);
    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod + " " + joinPoint);
    }

}
