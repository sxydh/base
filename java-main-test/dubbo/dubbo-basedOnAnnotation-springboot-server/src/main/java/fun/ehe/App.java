package fun.ehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan("fun.ehe.*")
@DubboComponentScan(basePackages = "fun.ehe.service")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
