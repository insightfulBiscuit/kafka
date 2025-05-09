package com.poc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ProducerApp {
    public static void main(String[] args) throws java.lang.InterruptedException{
        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        System.out.println("Consumer started. Waiting for messages...");

        ProducerRecord<String, String> record = new ProducerRecord<>("quickstart-events", "key", "value");
        
        int num = 0;
        while(num <= 20){
            record = new ProducerRecord<>("quickstart-events", "key", String.valueOf(num++));
            try {
                producer.send(record);
                System.out.println("Message sent successfully!");
            } catch (Exception e) {
                System.out.println("Message failed to send!");
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(1);
        }
        
        producer.flush();
        producer.close();
    }
}
