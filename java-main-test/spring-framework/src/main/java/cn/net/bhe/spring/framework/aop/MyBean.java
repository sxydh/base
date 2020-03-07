package cn.net.bhe.spring.framework.aop;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

    public void test() {
        String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println("Name of current method: " + nameofCurrMethod);
    }

}
