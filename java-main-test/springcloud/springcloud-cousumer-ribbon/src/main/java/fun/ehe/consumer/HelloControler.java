package fun.ehe.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    static final Logger LOGGER = LoggerFactory.getLogger(HelloControler.class);

    @Autowired
    HelloService helloService;

    @GetMapping(value = "/helloWorld")
    public String helloWorld() {
        LOGGER.info(this.toString() + " -> helloWorld()");
        return "Hello world";
    }

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        LOGGER.info(this + " -> hello( " + name + " )");
        return helloService.hello(name) + "  Consumer: Ribbon";
    }
}