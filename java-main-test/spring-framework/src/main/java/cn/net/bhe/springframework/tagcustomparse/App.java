package cn.net.bhe.springframework.tagcustomparse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("tagcustom.xml");
        SimpleDateFormat format = (SimpleDateFormat) context.getBean("dateFormat");
        System.out.println(format.format(new Date()));
    }

}
