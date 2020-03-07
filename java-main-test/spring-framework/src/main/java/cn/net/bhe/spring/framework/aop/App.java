package cn.net.bhe.spring.framework.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MyBean bean = (MyBean) context.getBean(MyBean.class);
        bean.test();
    }

}
