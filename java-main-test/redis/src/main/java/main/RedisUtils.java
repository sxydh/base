package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.util.SafeEncoder;

public enum RedisUtils {
    ;
    static Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);

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

    public static Long hset(String key, String field, String value, Integer seconds) {
        Jedis jedis = getJedis();
        Long status = jedis.hset(key, field, value);
        if (seconds != null) {
            jedis.expire(key, seconds);
        }
        jedis.close();
        return status;
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
     * @param keys
     * @param args
     * @return
     */
    public static Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = getJedis();
        Object obj = jedis.eval(script, keys, args);
        return obj;
    }

    public static void main(String[] main) {

    }
}
