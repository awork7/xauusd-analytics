package com.xauusd.ingestion.config;

import com.xauusd.ingestion.model.TickData;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic xauusdTicksTopic() {
        return TopicBuilder.name("xauusd-ticks")
                .partitions(3)         // 3 partitions for distributed processing scale
                .replicas(1)           // Single broker node setup match
                .build();
    }
}