package fun.ehe.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import fun.ehe.utils.JacksonUtils;

@Service
public class Consumer {

    static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = { "test" })
    public void consumer(ConsumerRecord<String, Object> message) throws JsonProcessingException {
        LOGGER.info(message.key());
        LOGGER.info(JacksonUtils.objToJsonStr(message.value()));
    }
}
