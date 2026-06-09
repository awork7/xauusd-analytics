package com.xauusd.analytics.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsConsumer {
    private static final Logger log = LoggerFactory.getLogger(AnalyticsConsumer.class);

    @KafkaListener(topics = "xauusd-ticks", groupId = "xauusd-analytics-group")
    public void consumeMarketTick(ConsumerRecord<String, String> record) {
        log.info("?? Consumer Intercepted Tick -> Key: {}, Payload: {}", record.key(), record.value());
    }
}
