package fun.ehe.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

import fun.ehe.bean.User;

@Component
public class Client {

    @Reference(version = "1.0.0")
    private Interface interfac;
    static Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @PostConstruct
    public void postConstruct() {
        String hello = interfac.sayHello("tom");
        List<User> list = interfac.getUsers();
        LOGGER.info(hello);
        LOGGER.info(list.toString());
    }

}
