package fun.ehe.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements HelloService {

    static final Logger LOGGER = LoggerFactory.getLogger(FallBack.class);

    @Override
    public String hello(String name) {
        LOGGER.info(this + " -> hello( " + name + " )");
        return "sorry " + name;
    }

}
