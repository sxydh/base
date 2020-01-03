package fun.ehe;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class App {

    @PostMapping("/redis-session")
    public Object redisSession(HttpServletRequest request) {
        String id = request.getSession().getId();
        request.getSession().setAttribute(id, id);
        return id;
    }

    @GetMapping("/get")
    public Object get(HttpServletRequest request) {
        String id = request.getSession().getId();
        return request.getSession().getAttribute(id);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}