package com.nhndooray.samplespringbatch.stats;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class WeeklyStat {

    private String statDate;
    private Long itemId;
    private Long clickCount;
    private Long exposeCount;
    private BigDecimal ctr;

    public WeeklyStat() {
    }

    public WeeklyStat calculateCtr() {
        this.ctr = BigDecimal.valueOf(clickCount).divide(BigDecimal.valueOf(exposeCount), 3, RoundingMode.HALF_EVEN);
        return this;
    }
}
