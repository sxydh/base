package cn.net.bhe.basics.base.function;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        test((Function<Map<String, Object>, Object>) m -> {
            for (String key : m.keySet()) {
                m.put(key, m.get(key) + " + " + UUID.randomUUID().toString());
            }
            return m;
        });
    }

    public static void test(Function<Map<String, Object>, Object> func) {
        Map<String, Object> t = new HashMap<>();
        t.put("key1", "value1");
        t.put("key2", "value2");
        Object r = func.apply(t);
        System.out.println("r => " + r);
    }

}
