package cn.net.bhe.basics.base.dict;

import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     * 如果v已经计算好了，那么适合使用putIfAbsent(k, v)，
     * 如果v还未计算，同时计算需要一些耗时，那么建议使用computeIfAbsent，将获取v值的计算放到lambada表达式体内，
     * 这样只有在map不含有k对应值时才会进行获取v值的计算，可以优化性能
     */
    public static void
    // computeIfAbsent
    main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        Object v = map.computeIfAbsent("1", k -> Integer.parseInt(k));
        System.out.println(v);
    }
    
}
