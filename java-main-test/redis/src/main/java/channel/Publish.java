package channel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.RedisUtils;
import redis.clients.jedis.Jedis;
import utils.SerializeUtils;

public class Publish {

    static final Logger LOGGER = LoggerFactory.getLogger(Publish.class);
    private Jedis jedis = RedisUtils.getJedis();

    public static void main(String[] args) throws Exception {
        Publish publish = new Publish();
        int i = 0;
        while (i < 20) {
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
            String message = bfReader.readLine();
            publish.publish("testChannel", message);
            i++;
        }
        publish.jedis.close();
    }

    public Long publish(String channel, Object message) throws Exception {
        System.out.printf("%-15s%-30s%-30s%n", "publish:", channel, message);
        return jedis.publish(SerializeUtils.serialize(channel), SerializeUtils.serialize(message));
    }

}
