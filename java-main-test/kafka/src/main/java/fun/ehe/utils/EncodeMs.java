package fun.ehe.utils;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodeMs implements Serializer<Object> {

    static final Logger LOGGER = LoggerFactory.getLogger(EncodeMs.class);

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object obj) {
        try {
            return SerializeUtils.serialize(obj);
        } catch (Exception e) {
            LOGGER.info(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void close() {

    }

}
