package com.poc.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.util.Properties;

import java.lang.InterruptedException;
import java.io.IOException;
import com.pi4j.io.i2c.I2CFactory;


public class ProducerApp {
    public static void main(String[] args) throws InterruptedException, IOException, I2CFactory.UnsupportedBusNumberException{
        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.33.130:9092");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        System.out.println("Producer started.");
        ProducerRecord<String, String> record_1;
        ProducerRecord<String, String> record_2;

        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        I2CDevice device = bus.getDevice(0x4B);
        
        int num = (int) 1e10;
        while(num++ < 1e10){
            device.write((byte) 0x84); // 1000 0100
			Thread.sleep(50);
			int raw_adc_1 = device.read() & 0xFF; // mask to unsigned int
			System.out.printf("Digital value of (A0) analog input: %d%n", raw_adc_1);
            record_1 = new ProducerRecord<>("quickstart-events", "key", String.valueOf(raw_adc_1));

            device.write((byte) 0x88); // 1000 1000
            Thread.sleep(50);
            int raw_adc_2 = device.read() & 0xFF; // mask to unsigned int
			System.out.printf("Digital value of (A3) analog input: %d%n", raw_adc_2);
            record_2 = new ProducerRecord<>("quickstart-events", "key", String.valueOf(raw_adc_2));

            Thread.sleep(500);
            System.out.println();

            try {
                producer.send(record_1);
                producer.send(record_2);
                System.out.println("Messages sent successfully!");
            } catch (Exception e) {
                System.out.println("Messages failed to send!");
                e.printStackTrace();
            }
        }
        
        producer.flush();
        producer.close();
    }
}