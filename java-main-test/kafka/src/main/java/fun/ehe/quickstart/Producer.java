package fun.ehe.quickstart;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fun.ehe.bean.Message;
import fun.ehe.utils.MathUtils;

public class Producer {

    static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) throws Exception {
        // assign topicName to string variable
        String topicName = "test";
        // create instance for properties to access producer configs
        Properties props = new Properties();
        // assign localhost id
        props.put("bootstrap.servers", "localhost:9092");
        // aet acknowledgements for producer requests
        props.put("acks", "all");
        // if the request fails, the producer can automatically retry
        props.put("retries", 0);
        // specify buffer size in config
        props.put("batch.size", 16384);
        // reduce the no of requests less than 0
        props.put("linger.ms", 1);
        // the buffer.memory controls the total amount of memory available to the producer for buffering
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "fun.ehe.utils.EncodeMs");

        org.apache.kafka.clients.producer.Producer<String, Object> producer = new KafkaProducer<String, Object>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, Object>(topicName, Integer.toString(i), new Message(MathUtils.getUUID())), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (metadata != null) {
                        LOGGER.info(metadata + " send successfully");
                    }
                    if (exception != null) {
                        LOGGER.info(metadata + " failed to send");
                    }
                }
            });
        }
        LOGGER.info("all messages have been sent");
        producer.close();
    }
}
