package fun.ehe.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        LOGGER.info("provider: " + port + " -> helloWorld()");
        return "Hello World";
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        LOGGER.info("provider: " + port + " -> hello( " + name + " )");
        return "Hello " + name + " , i'm from " + port;
    }
}
