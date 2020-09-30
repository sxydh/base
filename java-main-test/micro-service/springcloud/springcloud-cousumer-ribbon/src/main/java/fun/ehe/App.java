package fun.ehe;

import org.springframework.boot.SpringApplication;

import fun.ehe.consumer.ConsumerApplication;

public class App {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "consumer");
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
