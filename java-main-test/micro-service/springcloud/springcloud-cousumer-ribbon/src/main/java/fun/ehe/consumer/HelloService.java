package fun.ehe.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;
    static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

    @HystrixCommand(fallbackMethod = "fallback")
    public String hello(String name) {
        LOGGER.info(this + " -> http://SERVER/hello?name=" + name);
        return restTemplate.getForObject("http://PROVIDER/hello?name=" + name, String.class);
    }

    public String fallback(String name) {
        return "hi, " + name + ", sorry, error!";
    }

}