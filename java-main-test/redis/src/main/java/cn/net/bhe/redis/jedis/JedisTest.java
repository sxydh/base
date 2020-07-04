package cn.net.bhe.redis.jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.util.SafeEncoder;

public class JedisTest {
    static Logger log = LoggerFactory.getLogger(JedisTest.class);

    private static JedisPool jedisPool;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(100);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, "127.0.0.1", 6379);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static String get(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    
    public static Map<String, Response<String>> pipelineGet(List<String> ks) {
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        Map<String, Response<String>> ret = new HashMap<String, Response<String>>();
        for (String k : ks) {
            ret.put(k, pipeline.get(k));
        }
        pipeline.sync();
        jedis.close();
        return ret;
    }
    
    @Test
    void pipelineGetTest() {
        long start = System.currentTimeMillis();
        List<String> ks = new ArrayList<String>();
        for (int i = 1000000; i > 0; i--) {
            ks.add(String.valueOf(i));
        }
        Map<String, Response<String>> ret = pipelineGet(ks);
        log.info("耗时：{}，总数：{}，取一：{}", System.currentTimeMillis() - start, ret.size(), ret.entrySet().iterator().next());
    }

    public static String hget(String key, String field) {
        Jedis jedis = getJedis();
        String value = jedis.hget(key, field);
        jedis.close();
        return value;
    }

    public static byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        byte[] value = jedis.get(key);
        jedis.close();
        return value;
    }

    public static byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = getJedis();
        byte[] value = jedis.hget(key, field);
        jedis.close();
        return value;
    }

    public static String set(String key, String value, Integer seconds) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value);
        if (seconds != null) {
            jedis.expire(key, seconds);
        }
        jedis.close();
        return status;
    }
    
    @Test
    void setTest() {
        long start = System.currentTimeMillis();
        for (int i = 1000000; i > 0; i--) {
            set(String.valueOf(i), String.valueOf(i), null);
        }
        log.info("耗时：{}", System.currentTimeMillis() - start);
    }
    
    public static void pipelineSet(Map<String, String> kvs, Integer seconds) {
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        for (Entry<String, String> entry : kvs.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            pipeline.set(key, val);
            if (seconds != null) {
                pipeline.expire(key, seconds);
            }
        }
        pipeline.sync();
        jedis.close();
    }
    
    @Test
    void pipelineSetTest() {
        long start = System.currentTimeMillis();
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 1000000; i > 0; i--) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        pipelineSet(map, 3600);
        log.info("耗时：{}", System.currentTimeMillis() - start);
    }

    public static Long hset(String key, String field, String value, Integer seconds) {
        Jedis jedis = getJedis();
        Long status = jedis.hset(key, field, value);
        if (seconds != null) {
            jedis.expire(key, seconds);
        }
        jedis.close();
        return status;
    }
    
    public static void hset(List<String> ks, List<String> fs, List<String> vs, Integer seconds) {
        Jedis jedis = getJedis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < ks.size(); i++) {
            String key = ks.get(i);
            String field = fs.get(i);
            String val = vs.get(i);
            pipeline.hset(key, field, val);
            if (seconds != null) {
                pipeline.expire(key, seconds);
            }
        }
        pipeline.sync();
        jedis.close();
    }
    
    @Test
    void pipelineHsetTest() {
        long start = System.currentTimeMillis();
        List<String> ks = new ArrayList<String>();
        List<String> fs = new ArrayList<String>();
        List<String> vs = new ArrayList<String>();
        for (int i = 1000000; i > 0; i--) {
            ks.add("0");
            fs.add(String.valueOf(i));
            vs.add(String.valueOf(i));
        }
        hset(ks, fs, vs, 1000);
        log.info("耗时：{}", System.currentTimeMillis() - start);
    }

    public static String set(byte[] key, byte[] value, Integer seconds) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value);
        if (seconds != null) {
            jedis.expire(key, seconds);
        }
        jedis.close();
        return status;
    }

    public static String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long seconds) {
        Jedis jedis = getJedis();
        String status = jedis.set(key, value, nxxx, expx, seconds);
        jedis.close();
        return status;
    }

    public static Long hset(byte[] key, byte[] field, byte[] value, Integer seconds) {
        Jedis jedis = getJedis();
        Long status = jedis.hset(key, field, value);
        if (seconds != null) {
            jedis.expire(key, seconds);
        }
        jedis.close();
        return status;
    }

    public static Long del(String key) {
        Jedis jedis = getJedis();
        Long status = jedis.del(key);
        jedis.close();
        return status;
    }

    public static Long del(byte[]... keys) {
        Jedis jedis = getJedis();
        Long status = jedis.del(keys);
        jedis.close();
        return status;
    }

    /**
     * Note that pattern does not distinguish between key types.
     * <p/>
     * Since these commands allow for incremental iteration, returning only a
     * small number of elements per call, they can be used in production without
     * the downside of commands like KEYS or SMEMBERS that may block the server
     * for a long time (even several seconds) when called against big
     * collections of keys or elements.
     * <p/>
     * However while blocking commands like SMEMBERS are able to provide all the
     * elements that are part of a Set in a given moment, The SCAN family of
     * commands only offer limited guarantees about the returned elements since
     * the collection that we incrementally iterate can change during the
     * iteration process.
     *
     * @param pattern
     * @return
     */
    public static Set<byte[]> scanResultGet(String pattern) {
        Jedis jedis = getJedis();
        Set<byte[]> keys = new HashSet<>();
        ScanParams params = new ScanParams().match(pattern);
        String nextCursor = "0";
        do {
            ScanResult<byte[]> scanResult = jedis.scan(SafeEncoder.encode(nextCursor), params);
            nextCursor = SafeEncoder.encode(scanResult.getCursorAsBytes());
            keys.addAll(scanResult.getResult());
        } while (!nextCursor.equals("0"));
        jedis.close();
        return keys;
    }

    public static Long expire(String key, int seconds) {
        Jedis jedis = getJedis();
        Long status = jedis.expire(key, seconds);
        jedis.close();
        return status;
    }

    /**
     * 脚本执行是原子操作
     * 
     * 例：if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end
     * 
     * Redis uses the same Lua interpreter to run all the commands. Also Redis
     * guarantees that a script is executed in an atomic way: no other script or
     * Redis command will be executed while a script is being executed. This
     * semantic is similar to the one of MULTI / EXEC. From the point of view of
     * all the other clients the effects of a script are either still not
     * visible or already completed.
     * <p/>
     * However this also means that executing slow scripts is not a good idea.
     * It is not hard to create fast scripts, as the script overhead is very
     * low, but if you are going to use slow scripts you should be aware that
     * while the script is running no other client can execute commands.
     *
     * @param script
     * @param KEYS      索引。例，删除KEY[1]：redis.call('del', KEYS[1])，注意从1开始
     * @param ARGS      参数。例，声明一个本地变量并赋值为ARGV[1]：local t = ARGV[1]，注意从1开始
     * @return
     */
    public static Object eval(String script, List<String> KEYS, List<String> ARGS) {
        Jedis jedis = getJedis();
        Object obj = jedis.eval(script, KEYS, ARGS);
        return obj;
    }
    
    @Test
    void evalTest() {
        try {
            JedisTest.set("key1", "arg1", 3600);
            Thread.sleep(1000);
            List<String> keys = new ArrayList<>();
            keys.add("key1");
            List<String> args = new ArrayList<>();
            args.add("arg1");
            Object status = JedisTest.eval(
                    // script
                    "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end"
                    // keys
                    , keys
                    // arguments
                    , args);
            log.info(status + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
