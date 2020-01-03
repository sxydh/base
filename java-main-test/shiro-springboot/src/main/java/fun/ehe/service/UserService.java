package fun.ehe.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import fun.ehe.bean.entity.User;
import fun.ehe.dao.DataSource;

@Component
public class UserService {

    public User getUser(String username) {
        if (!DataSource.getData().containsKey(username)) {
            return null;
        }
        User user = new User();
        Map<String, String> detail = DataSource.getData().get(username);
        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}
