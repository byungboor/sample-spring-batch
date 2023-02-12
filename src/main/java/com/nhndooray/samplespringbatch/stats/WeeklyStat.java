package com.nhndooray.samplespringbatch.stats;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

// TODO - 02
//   - @Data 를 사용한 java bean 이다.
//   - BigDecimal 을 사용해야 정확한 계산을 할 수 있다.
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
