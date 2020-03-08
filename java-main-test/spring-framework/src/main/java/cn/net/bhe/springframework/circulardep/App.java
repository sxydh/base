package cn.net.bhe.springframework.circulardep;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        /*
         * org.springframework.beans.factory.support.AbstractBeanFactory
         * protected <T> T doGetBean(final String name, @Nullable final Class<T> requiredType,
         *         @Nullable final Object[] args, boolean typeCheckOnly) throws BeansException
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("circulardep.xml");
        TestA testA = (TestA) context.getBean("testA");
        System.out.println(testA);
    }
    
}
