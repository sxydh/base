package queue;

import main.RedisUtils;
import redis.clients.jedis.Jedis;
import utils.SerializeUtils;

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
        Jedis jedis = RedisUtils.getJedis();
        jedis.lpush(SerializeUtils.serialize(key), SerializeUtils.serialize(message));
        jedis.close();
    }

    public static Object lpop(String key) throws Exception {
        Jedis jedis = RedisUtils.getJedis();
        byte[] bytes = jedis.lpop(SerializeUtils.serialize(key));
        Object message = SerializeUtils.deserialize(bytes);
        jedis.close();
        return message;
    }

}
