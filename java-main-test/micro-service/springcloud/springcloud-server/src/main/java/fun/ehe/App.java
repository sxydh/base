package fun.ehe;

import org.springframework.boot.SpringApplication;

import fun.ehe.server.ServerApplication;

public class App {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "server");
        SpringApplication.run(ServerApplication.class, args);
    }

}
