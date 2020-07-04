package cn.net.bhe.redis.redistemplate;

import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisPoolingClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Helper class that simplifies Redis data access code.
 * <p/>
 * Performs automatic serialization/deserialization between the given objects
 * and the underlying binary data in the Redis store. By default, it uses Java
 * serialization for its objects (through JdkSerializationRedisSerializer ). For
 * String intensive operations consider the dedicated StringRedisTemplate.
 * <p/>
 * The central method is execute, supporting Redis access code implementing the
 * RedisCallback interface. It provides RedisConnection handling such that
 * neither the RedisCallback implementation nor the calling code needs to
 * explicitly care about retrieving/closing Redis connections, or handling
 * Connection lifecycle exceptions. For typical single step actions, there are
 * various convenience methods.
 * <p/>
 * Once configured, this class is thread-safe.
 *
 */
public enum RedisTemplateTest {

    ;
    private static RedisTemplate<String, String> redisTemplate;
    private static RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    private static HashOperations<String, Object, Object> hashOperations;

    static {
        // RedisStandaloneConfiguration
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("127.0.0.1");
        redisStandaloneConfiguration.setPort(6379);
        // JedisClientConfiguration
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxWaitMillis(1000);
        poolConfig.setTestOnBorrow(true);
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb = (JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        jpcb.poolConfig(poolConfig);
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        // JedisConnectionFactory
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
        //
        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        //
        hashOperations = redisTemplate.opsForHash();
    }

    public static void main(String[] args) {
        System.out.println(RedisTemplateTest.getExpire("key3"));
    }

    public static Long set(String key, String value, long seconds) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] bytekey = redisSerializer.serialize(key);
            byte[] bytevalue = redisSerializer.serialize(value);
            connection.set(bytekey, bytevalue);
            connection.expire(bytekey, seconds);
            return 1L;
        });
    }

    public static Long getTime(String key) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            long time = connection.ttl(redisSerializer.serialize(key));
            return time;
        });
    }

    public static Set<String> keys(String pattern) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> redisTemplate.keys("*" + pattern + "*"));
    }

    public static String get(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> redisSerializer.deserialize(connection.get(redisSerializer.serialize(key))));
    }

    public static void put(String key, Object hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    public static Object get(String key, Object hashKey) {
        return hashOperations.get(key, hashKey);
    }

    public static void delete(String key, Object... hashKeys) {
        hashOperations.delete(key, hashKeys);
    }

    public static Map<Object, Object> entries(String key) {
        return hashOperations.entries(key);
    }

    public static long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

}
