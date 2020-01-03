package cn.net.bhe.basics.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {

    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.putIfAbsent("key", "value");
    }
}
