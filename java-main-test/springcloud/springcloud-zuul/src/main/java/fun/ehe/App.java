package fun.ehe;

import org.springframework.boot.SpringApplication;

import fun.ehe.zuul.ServiceZuulApplication;

public class App {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "zuul");
        SpringApplication.run(ServiceZuulApplication.class, args);
    }

}
