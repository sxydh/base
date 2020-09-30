package fun.ehe.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /*Compiler error, ignoring that because the Bean was injected at the time the program started, the compiler did not perceive it, so error.*/
    @Autowired
    HelloService helloService;
    static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        LOGGER.info(this + "(consumerWithFeign) -> hello( " + name + " )");
        return helloService.hello(name) + "  Consumer: Feign";
    }
}
