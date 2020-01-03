package channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.RedisUtils;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.Jedis;
import utils.SerializeUtils;

public class Subscribe extends BinaryJedisPubSub {

    static final Logger LOGGER = LoggerFactory.getLogger(Subscribe.class);
    private Jedis jedis = RedisUtils.getJedis();

    public static void main(String[] args) throws Exception {
        new Subscribe("testChannel");
    }

    public Subscribe(String channel) throws Exception {
        jedis.subscribe(this, SerializeUtils.serialize(channel));
        jedis.close();
    }

    @Override
    public void onMessage(byte[] channel, byte[] message) {
        try {
            System.out.printf("%-15s%-30s%-30s%n", "onMessage:", SerializeUtils.deserialize(channel),
                    SerializeUtils.deserialize(message));
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void onSubscribe(byte[] channel, int subscribedChannels) {
        try {
            System.out.printf("%-15s%-30s%-30s%n", "onSubscribe:", SerializeUtils.deserialize(channel),
                    String.valueOf(subscribedChannels));
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Override
    public void onUnsubscribe(byte[] channel, int subscribedChannels) {
        try {
            System.out.printf("%-15s%-30s%-30s%n", "onUnsubscribe:", SerializeUtils.deserialize(channel),
                    String.valueOf(subscribedChannels));
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

}
