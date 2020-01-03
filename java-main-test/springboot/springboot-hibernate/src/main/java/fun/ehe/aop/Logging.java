package fun.ehe.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging {

    static final Logger LOGGER = LoggerFactory.getLogger(Logging.class);

    @Pointcut("execution(public * fun.ehe.service.*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @AfterReturning(value = "pointcut()", returning = "returnVal")
    public void afterReturning(Object returnVal) throws Throwable {
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " => " + returnVal);
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(Throwable e) {
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " => " + e.getLocalizedMessage());
    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info(Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
