package fun.ehe.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;

import fun.ehe.bean.User;

@Service(version = "1.0.0")
public class InterfaceImpl implements Interface {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<User>();
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
