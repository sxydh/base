package fun.ehe.utils;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecodeMs implements Deserializer<Object> {

    static final Logger LOGGER = LoggerFactory.getLogger(DecodeMs.class);

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Object deserialize(String topic, byte[] bytes) {
        try {
            return SerializeUtils.deserialize(bytes);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void close() {

    }

}
