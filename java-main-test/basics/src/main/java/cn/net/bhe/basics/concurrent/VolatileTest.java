package cn.net.bhe.basics.concurrent;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * volatile并不能带来线程安全
 */
public class VolatileTest {
    static Logger log = LoggerFactory.getLogger(VolatileTest.class);

    /**
     * 加数测试，正确结果因为100000，实际结果总是小于正确结果
     */
    volatile int race = 0;
    @Test
    public void addNum() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread task = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        race++;
                    }
                }
            });
            task.start();
        }
        while (Thread.activeCount() > 2) { // 用了junit所以活动线程至少是2
            Thread.yield();
        }
        log.info(race + "");
    }
    
}