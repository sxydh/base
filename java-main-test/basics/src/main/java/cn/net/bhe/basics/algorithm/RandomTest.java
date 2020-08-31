package cn.net.bhe.basics.algorithm;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 随机数
 */
public class RandomTest {
    static Logger log = LoggerFactory.getLogger(RandomTest.class);

    /**
     * java.util.Random
     * 
     * Random用于生成伪随机数。此类的实例是线程安全的，但是，此类的实例在密码学上是不安全的。 
     */
    @Test
    public void random() {
        Random random = new Random();
        log.info(random.nextBoolean() + "");
        log.info(random.nextInt() + "");
    }
    
    /**
     * java.util.concurrent.ThreadLocalRandom
     * 
     * 参考Random，但适用于并发场景。
     */
    @Test
    public void threadLocalRandom() {
        Random random = ThreadLocalRandom.current();
        log.info(random.nextBoolean() + "");
        log.info(random.nextInt() + "");
    }

}
