package cn.net.bhe.springframework.beanfactorypostprocessor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
         new ClassPathXmlApplicationContext("beanfactorypostprocessor.xml");
    }
    
}
