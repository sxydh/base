package cn.net.bhe.redis.queue;

import cn.net.bhe.redis.jedis.JedisTest;
import cn.net.bhe.utils.main.SerializeUtils;
import redis.clients.jedis.Jedis;

public class Queue {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 5; i++) {
            lpush("test", i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(lpop("test"));
        }
    }

    public static void lpush(String key, Object message) throws Exception {
        Jedis jedis = JedisTest.getJedis();
        jedis.lpush(SerializeUtils.serialize(key), SerializeUtils.serialize(message));
        jedis.close();
    }

    public static Object lpop(String key) throws Exception {
        Jedis jedis = JedisTest.getJedis();
        byte[] bytes = jedis.lpop(SerializeUtils.serialize(key));
        Object message = SerializeUtils.deserialize(bytes);
        jedis.close();
        return message;
    }

}
