package cn.net.bhe.basics.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持并发访问的哈希表
 */
public class ConcurrentMapTest {
    static Logger log = LoggerFactory.getLogger(ConcurrentMapTest.class);

    /**
     * 仅当键不存在时才放入值，线程安全
     */
    @Test
    public void putIfAbsent() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.putIfAbsent("key", "value");
        map.putIfAbsent("key", "new value");
        log.info(map + "");
    }
}
