package fun.ehe.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fun.ehe.bean.User;

@Component
public class Client {

    @Autowired
    private Interface interfac;
    static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @Scheduled(fixedDelay = 20 * 1000)
    public void scheduled() {
        String hello = interfac.sayHello("tom");
        List<User> list = interfac.getUsers();
        String random = interfac.getRandom();
        LOGGER.info(hello);
        LOGGER.info(list.toString());
        LOGGER.info(random);
    }
}
