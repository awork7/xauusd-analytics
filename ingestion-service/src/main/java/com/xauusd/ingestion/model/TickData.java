package com.xauusd.ingestion.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TickData {
    private String symbol;      // Will always be "XAUUSD"
    private double bid;         // Current buying price
    private double ask;         // Current selling price
    private double volume;      // Liquidity/Volume size of the tick
    private long timestamp;     // Epoch timestamp in milliseconds
}