package utils;

import java.util.HashSet;
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
    private static Integer max_active = 100;
    private static Integer max_idle = 100;
    private static Integer max_wait = 1000;
    private static Boolean test_on_borrow = true;
    private static String url = "127.0.0.1";
    private static Integer port = 6379;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(max_active);
        config.setMaxIdle(max_idle);
        config.setMaxWaitMillis(max_wait);
        config.setTestOnBorrow(test_on_borrow);
        jedisPool = new JedisPool(config, url, port);
    }

    private static Jedis getJedis() {
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
}
