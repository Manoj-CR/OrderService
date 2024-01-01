package org.example.orderservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.example.orderservice.kafka.dto.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderProducer {


    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);
    private NewTopic topic;

    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(PaymentEvent paymentEvent){
        logger.info(String.format("Order Event => %s", paymentEvent.toString()));

        kafkaTemplate.send(topic.name(), paymentEvent);
    }



}
