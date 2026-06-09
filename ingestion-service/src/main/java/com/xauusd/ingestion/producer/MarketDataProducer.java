package com.xauusd.ingestion.producer;

import com.xauusd.ingestion.model.TickData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class MarketDataProducer {

    private final KafkaTemplate<String, TickData> kafkaTemplate;
    private final Random random = new Random();
    
    // Starting baseline value for XAUUSD spot rate
    private double currentPrice = 2350.00; 

    @Scheduled(fixedRate = 200) // Fires 5 times every single second
    public void generateMarketTicks() {
        // Generate a minor price shift (-0.45 to +0.45 cents)
        double priceChange = (random.nextDouble() - 0.5) * 0.90;
        currentPrice += priceChange;
        
        // Round cleanly to 2 decimal places (standard financial pips layout)
        currentPrice = Math.round(currentPrice * 100.0) / 100.0;
        
        double spread = 0.15; // Tight 15-cent bid/ask trading spread
        double bid = currentPrice;
        double ask = currentPrice + spread;
        double volume = Math.round((10 + random.nextDouble() * 490) * 10.0) / 10.0; // Lot sizes

        TickData tick = new TickData("XAUUSD", bid, ask, volume, System.currentTimeMillis());

        // Stream the structured JSON payload asynchronously to Kafka
        kafkaTemplate.send("xauusd-ticks", tick.getSymbol(), tick)
            .whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("🎯 Pushed Tick -> Price: ${} | Vol: {} oz", bid, volume);
                } else {
                    log.error("❌ Transmission failure to Kafka: {}", ex.getMessage());
                }
            });
    }
}