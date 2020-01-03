package fun.ehe.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;

import fun.ehe.bean.User;

/**
 * @see <a href=
 *      "https://dubbo.incubator.apache.org/zh-cn/docs/user/references/xml/introduction.html"
 *      >reference</a>
 */
@Service(
        //
        registry = "registryA",
        //
        interfaceClass = Interface.class,
        //
        group = "groupName",
        //
        version = "1.0.0",
        //
        timeout = 5000,
        //
        retries = 10,
        //
        loadbalance = "leastactive",
        //
        executes = 5,
        //
        protocol = "dubbo")
public class InterfaceImpl implements Interface {

    static final Logger LOGGER = LoggerFactory.getLogger(InterfaceImpl.class);

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        User u1 = new User();
        u1.setName("jack");
        u1.setAge(20);
        u1.setSex("male");

        User u2 = new User();
        u2.setName("tom");
        u2.setAge(21);
        u2.setSex("male");

        User u3 = new User();
        u3.setName("rose");
        u3.setAge(19);
        u3.setSex("female");

        list.add(u1);
        list.add(u2);
        list.add(u3);
        return list;
    }
}
