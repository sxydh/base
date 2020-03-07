package cn.net.bhe.spring.framework.conversionservice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("conversionservice.xml");
    }

}
