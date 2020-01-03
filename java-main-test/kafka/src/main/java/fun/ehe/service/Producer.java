package fun.ehe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import fun.ehe.bean.Message;
import fun.ehe.utils.JacksonUtils;
import fun.ehe.utils.MathUtils;

@Service
public class Producer {

    static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Scheduled(fixedDelay = 10 * 1000)
    public void send() throws JsonProcessingException {
        Message message = new Message(MathUtils.getUUID());
        kafkaTemplate.send("test", message);
        LOGGER.info("send() -> " + JacksonUtils.objToJsonStr(message));
    }

}
