package com.poc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;   // Kafka class to create a producer
import org.apache.kafka.clients.producer.ProducerRecord;  // Kafka class to send a record
import org.apache.kafka.clients.producer.ProducerConfig;  // Constants for producer configuration
import org.apache.kafka.common.serialization.StringSerializer;  // Serializer for keys and values

import java.util.Properties;


public class ProducerApp {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        ProducerRecord<String, String> record = new ProducerRecord<>("topic", "key", "value");
        producer.send(record);

        producer.flush();
        producer.close();
    }
}
