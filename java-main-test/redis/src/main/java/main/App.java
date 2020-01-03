package main;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] temp) throws Exception {
        ntset();
    }

    public static void eval() throws Exception {
        RedisUtils.set("key1", "arg1", 3600);
        Thread.sleep(1000);
        List<String> keys = new ArrayList<>();
        keys.add("key1");
        List<String> args = new ArrayList<>();
        args.add("arg1");
        Object status = RedisUtils.eval(
                // script
                "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end"
                // keys
                , keys
                // arguments
                , args);
        System.out.println(status);
    }

    /**
     * nxxx NX|XX, NX -- Only set the key if it does not already exist. XX --
     * Only set the key if it already exist.
     * <p/>
     * expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     */
    public static void ntset() {
        RedisUtils.getJedis().set("key1", "value1", "NX", "EX", 3600l);
    }
}
