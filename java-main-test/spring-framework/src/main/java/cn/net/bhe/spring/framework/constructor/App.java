package cn.net.bhe.spring.framework.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("constructor.xml");
        Bean bean = (Bean) context.getBean("bean");
        System.out.println(bean);
    }

}
