package com.poc.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.util.Properties;
import java.time.Duration;
import java.util.Collections;
import java.time.LocalTime;


public class ConsumerApp {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.32.72:9092");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, Double> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("input-topic"));

        System.out.println("Consumer started. Waiting for messages...");

        while (true) {
            ConsumerRecords<String, Double> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Double> record : records) {
                System.out.printf("Received message: key=%s, value=%s, partition=%d, offset=%d%n",
                                  record.key(), record.value(), record.partition(), record.offset());
                System.out.println("Messages received successfully! at " + LocalTime.now());
            }
        }
    }
}
