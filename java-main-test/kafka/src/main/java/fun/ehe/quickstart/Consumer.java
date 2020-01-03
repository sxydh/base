package fun.ehe.quickstart;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {

    static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) throws Exception {
        // kafka consumer configuration settings
        String topicName = "test";
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "fun.ehe.utils.DecodeMs");
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(props);

        // kafka consumer subscribes list of topics here
        consumer.subscribe(Arrays.asList(topicName));

        // print the topic name
        LOGGER.info("have subscribed to topic: " + topicName);
        int i = 0;
        while (i < 100) {
            ConsumerRecords<String, Object> records = consumer.poll(100);
            for (ConsumerRecord<String, Object> record : records) {
                // print the offset,key and value for the consumer records
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
            }
            i++;
        }
        consumer.close();
    }
}