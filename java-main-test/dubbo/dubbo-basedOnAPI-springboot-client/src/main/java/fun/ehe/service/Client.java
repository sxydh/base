package fun.ehe.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import fun.ehe.bean.User;
import fun.ehe.utils.DubboClientUtils;

@Component
public class Client {

    private Interface interfac;
    static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @PostConstruct
    public void postConstruct() {
        interfac = DubboClientUtils.get(Interface.class);
    }

    @Scheduled(fixedDelay = 10 * 1000)
    public void scheduled() {
        String hello = interfac.sayHello("tom");
        List<User> list = interfac.getUsers();
        String random = interfac.getRandom();
        LOGGER.info(hello);
        LOGGER.info(list.toString());
        LOGGER.info(random);
    }
}
