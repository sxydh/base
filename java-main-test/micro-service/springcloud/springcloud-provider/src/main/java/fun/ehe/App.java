package fun.ehe;

import org.springframework.boot.SpringApplication;

import fun.ehe.provider.ProviderApplication;

public class App {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "provider");
        SpringApplication.run(ProviderApplication.class, args);
    }

}
