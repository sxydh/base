package fun.ehe.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

import fun.ehe.bean.User;

/**
 * @see <a href=
 *      "https://dubbo.incubator.apache.org/zh-cn/docs/user/references/xml/introduction.html"
 *      >reference</a>
 */
@Component
public class Client {

    @Reference(
            //
            registry = "registryA",
            //
            version = "1.0.0",
            //
            group = "groupName",
            //
            actives = 15)
    private Interface interfac;
    static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @PostConstruct
    public void postConstruct() {
        String hello = interfac.sayHello("tom");
        List<User> list = interfac.getUsers();
        LOGGER.info(hello);
        LOGGER.info(list.toString());
    }
}
