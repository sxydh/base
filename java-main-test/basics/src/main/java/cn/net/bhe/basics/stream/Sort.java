package cn.net.bhe.basics.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sort {
    static final Logger log = LoggerFactory.getLogger(Sort.class);

    @Test
    public void example() {
        List<String> list = Arrays.asList(new String[] {"a", "c", "b", "a"});
        log.info(list.stream()
                .sorted((ele1, ele2) -> ele1.compareTo(ele2))
                .collect(Collectors.toList()) + "");
    }

}
